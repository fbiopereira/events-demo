package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.configexchange.FanoutExchangeCreator;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.FanoutExchangeRequest;
import dev.fbiopereira.eventsdemo.fanoutexchange.producer.FanoutEventProducer;
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
@RequestMapping("/api/exchanges")
@Tag(name = "Administration", description = "Operações para gerenciar exchanges e publicar mensagens no RabbitMQ")
public class FanoutExchangeController {

    private final FanoutExchangeCreator fanoutExchangeCreator;


    @Autowired
    public FanoutExchangeController(FanoutExchangeCreator fanoutExchangeCreator,
                                   FanoutEventProducer eventProducer) {
        this.fanoutExchangeCreator = fanoutExchangeCreator;
    }

    @PostMapping("/fanout")
    @Operation(
            summary = "Criar exchange fanout",
            description = "Cria uma nova exchange do tipo fanout no RabbitMQ com os parâmetros especificados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Exchange criada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar a exchange")
    })
    public ResponseEntity<String> createFanoutExchange(@RequestBody FanoutExchangeRequest request) {
        try {
            if (request.getExchangeName() == null || request.getExchangeName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome da exchange não pode ser vazio");
            }

            fanoutExchangeCreator.createFanoutExchange(
                    request.getExchangeName(),
                    request.isDurable(),
                    request.isAutoDelete()
            );

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Exchange '" + request.getExchangeName() + "' criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao criar a exchange: " + e.getMessage());
        }
    }


}
