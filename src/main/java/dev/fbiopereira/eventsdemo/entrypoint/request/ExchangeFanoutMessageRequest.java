package dev.fbiopereira.eventsdemo.entrypoint.request;

import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventSchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ExchangeFanoutMessageRequest {

    @Schema(description = "Nome da exchange que vai receber a mensagem", example = "notifications.fanout")
    private String exchangeName;

    @Schema(description = "Tipo do Evento a ser enviado", example = "corp.order")
    private String eventType;

    @Schema(description = "Assunto da mensagem", example = "order.created")
    private String subject;

    @Schema(description = "Nome da fila caso queira reeviar uma mensagem", example = "queue01")
    private String routingKey;

    @Schema(description = "Payload/JSON da mensagem", example = """
                    {                     
                        "objectId": "12345",
                        "objectDetail": "Detalhes do objeto processado"                     
                    }
                    """)
    private String payload;


    // Constructors
    public ExchangeFanoutMessageRequest() {
    }

    public ExchangeFanoutMessageRequest(String exchangeName, String eventType, String subject, String routingKey, String payload) {
        this.exchangeName = exchangeName;
        this.eventType = eventType;
        this.subject = subject;
        this.routingKey = routingKey;
        this.payload = payload;
    }

    // Getters and Setters

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
