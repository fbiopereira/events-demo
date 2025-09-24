package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventRequest;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@Tag(name = "Messages", description = "Operações para enviar mensagens para o RabbitMQ")
public class FanoutEventProducerController {

    private final FanoutEventProducer eventProducer;

    @Autowired
    public FanoutEventProducerController(FanoutEventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @PostMapping("/publish")
    @Operation(
            summary = "Publicar CloudEvent em uma exchange fanout",
            description = "Publica uma mensagem no formato CloudEvents 1.0 em uma exchange fanout existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Mensagem publicada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao publicar a mensagem")
    })
    public ResponseEntity<?> publishCloudEvent(@RequestBody CloudEventRequest request) {
        try {
            // Validação dos campos obrigatórios
            if (request.getExchangeName() == null || request.getExchangeName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Nome da exchange não pode ser vazio");
            }
            if (request.getEventType() == null || request.getEventType().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Tipo do evento não pode ser vazio");
            }
            if (request.getPayload() == null) {
                return ResponseEntity.badRequest().body("Payload não pode ser nulo");
            }

            // Publicar o evento usando o producer
            String eventId = eventProducer.publishEvent(
                    request.getExchangeName(),
                    request.getEventType(),
                    request.getPayload(),
                    request.getSubject()
            );

            // Retorna o ID do evento gerado para rastreabilidade
            Map<String, String> response = new HashMap<>();
            response.put("eventId", eventId);
            response.put("message", "CloudEvent publicado com sucesso na exchange '" + request.getExchangeName() + "'");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao publicar mensagem: " + e.getMessage());
        }
    }

}
