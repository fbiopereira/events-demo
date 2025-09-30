package dev.fbiopereira.eventsdemo.fanoutexchange.rest.controller;

import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventRequest;
import dev.fbiopereira.eventsdemo.fanoutexchange.service.FanoutMessageService;
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

    private final FanoutMessageService fanoutMessageService;

    @Autowired
    public FanoutEventProducerController(FanoutMessageService fanoutMessageService) {
        this.fanoutMessageService = fanoutMessageService;
    }

    @PostMapping("/publish")
    @Operation(
            summary = "Publicar CloudEvent em uma exchange fanout",
            description = "Publica uma mensagem no formato CloudEvents 1.0 em uma exchange fanout existente. " +
                         "A mensagem será validada contra o esquema definido para a exchange antes da publicação."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Mensagem publicada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))
            ),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou validação de esquema falhou"),
            @ApiResponse(responseCode = "404", description = "Exchange não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro ao publicar a mensagem")
    })
    public ResponseEntity<?> publishCloudEvent(@RequestBody CloudEventRequest request) {
        try {
            // Usar o service para validar e publicar a mensagem
            String eventId = fanoutMessageService.validateAndPublishMessage(request);

            // Retorna o ID do evento gerado para rastreabilidade
            Map<String, String> response = new HashMap<>();
            response.put("eventId", eventId);
            response.put("message", "CloudEvent publicado com sucesso na exchange '" + request.getExchangeName() + "'");

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao publicar mensagem: " + e.getMessage());
        }
    }
}
