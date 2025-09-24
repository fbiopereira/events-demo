package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.configqueue.FanoutQueueCreator;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.FanoutQueueRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/queues")
@Tag(name = "Administration", description = "Operações para gerenciar filas no RabbitMQ")
public class FanoutQueueController {

    private final FanoutQueueCreator fanoutQueueCreator;

    @Autowired
    public FanoutQueueController(FanoutQueueCreator fanoutQueueCreator) {
        this.fanoutQueueCreator = fanoutQueueCreator;
    }

    @PostMapping("/fanout")
    @Operation(
            summary = "Criar fila associada a exchange fanout",
            description = "Cria uma nova fila no RabbitMQ e a associa a uma exchange fanout existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Fila criada e associada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a fila ou associação")
    })
    public ResponseEntity<String> createFanoutQueue(@RequestBody FanoutQueueRequest request) {
        try {
            // Validação dos campos obrigatórios
            if (request.getQueueName() == null || request.getQueueName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome da fila não pode ser vazio");
            }

            if (request.getExchangeName() == null || request.getExchangeName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome da exchange não pode ser vazio");
            }

            // Cria a fila e a associa à exchange fanout
            fanoutQueueCreator.createAndBindQueue(
                    request.getQueueName(),
                    request.getExchangeName(),
                    request.isDurable(),
                    request.isAutoDelete(),
                    request.isExclusive()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Fila '" + request.getQueueName() + "' criada e associada à exchange '" +
                          request.getExchangeName() + "' com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar a fila: " + e.getMessage());
        }
    }
}
