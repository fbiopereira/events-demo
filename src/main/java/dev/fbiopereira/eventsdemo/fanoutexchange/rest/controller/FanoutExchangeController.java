package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.configexchange.FanoutExchangeCreator;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.FanoutExchangeRequest;
import dev.fbiopereira.eventsdemo.fanoutexchange.document.FanoutExchangeDocument;
import dev.fbiopereira.eventsdemo.fanoutexchange.service.FanoutExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/exchanges")
@Tag(name = "Administration", description = "Operações para gerenciar exchanges e publicar mensagens no RabbitMQ")
public class FanoutExchangeController {

    private final FanoutExchangeCreator fanoutExchangeCreator;
    private final FanoutExchangeService fanoutExchangeService;

    @Autowired
    public FanoutExchangeController(FanoutExchangeCreator fanoutExchangeCreator,
                                   FanoutExchangeService fanoutExchangeService) {
        this.fanoutExchangeCreator = fanoutExchangeCreator;
        this.fanoutExchangeService = fanoutExchangeService;
    }

    @PostMapping("/fanout")
    @Operation(
            summary = "Criar exchange fanout",
            description = "Cria uma nova exchange do tipo fanout no RabbitMQ com os parâmetros especificados e salva as configurações no MongoDB incluindo o esquema CloudEvent 1.0"
    )
    @ApiResponse(responseCode = "201", description = "Exchange criada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou esquema CloudEvent inválido")
    @ApiResponse(responseCode = "500", description = "Erro ao criar a exchange")
    public ResponseEntity<Map<String, Object>> createFanoutExchange(@RequestBody FanoutExchangeRequest request) {
        try {
            LocalDateTime requestTimestamp = LocalDateTime.now();

            // Validação básica
            if (request.getExchangeName() == null || request.getExchangeName().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Nome da exchange não pode ser vazio"));
            }

            if (request.getCloudEventSchema() == null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Esquema CloudEvent é obrigatório"));
            }

            // Verifica se já existe uma exchange com o mesmo nome
            FanoutExchangeDocument existingExchange = fanoutExchangeService.findByExchangeName(request.getExchangeName());
            if (existingExchange != null) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Exchange com nome '" + request.getExchangeName() + "' já existe"));
            }

            // Salva no MongoDB primeiro (isso fará a validação do CloudEvent)
            FanoutExchangeDocument savedDocument = fanoutExchangeService.saveFanoutExchange(request, requestTimestamp);

            // Cria a exchange no RabbitMQ
            fanoutExchangeCreator.createFanoutExchange(
                    request.getExchangeName(),
                    request.isDurable(),
                    request.isAutoDelete()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(Map.of(
                        "message", "Exchange '" + request.getExchangeName() + "' criada com sucesso",
                        "exchangeName", request.getExchangeName(),
                        "documentId", savedDocument.getId(),
                        "createdAt", savedDocument.getCreatedAt(),
                        "requestTimestamp", savedDocument.getRequestTimestamp(),
                        "cloudEventSchema", savedDocument.getCloudEventSchema()
                    ));

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("error", "Erro de validação: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao criar a exchange: " + e.getMessage()));
        }
    }

    @GetMapping("/fanout")
    @Operation(
            summary = "Listar exchanges fanout",
            description = "Lista todas as exchanges fanout registradas no MongoDB"
    )
    @ApiResponse(responseCode = "200", description = "Lista de exchanges recuperada com sucesso",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    public ResponseEntity<List<FanoutExchangeDocument>> getAllFanoutExchanges() {
        try {
            List<FanoutExchangeDocument> exchanges = fanoutExchangeService.findAll();
            return ResponseEntity.ok(exchanges);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fanout/{exchangeName}")
    @Operation(
            summary = "Buscar exchange fanout por nome",
            description = "Busca uma exchange fanout específica pelo nome"
    )
    @ApiResponse(responseCode = "200", description = "Exchange encontrada",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = FanoutExchangeDocument.class)))
    @ApiResponse(responseCode = "404", description = "Exchange não encontrada")
    public ResponseEntity<FanoutExchangeDocument> getFanoutExchangeByName(@PathVariable String exchangeName) {
        try {
            FanoutExchangeDocument exchange = fanoutExchangeService.findByExchangeName(exchangeName);
            if (exchange != null) {
                return ResponseEntity.ok(exchange);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
