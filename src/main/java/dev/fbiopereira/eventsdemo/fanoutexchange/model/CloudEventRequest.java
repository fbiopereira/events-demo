package dev.fbiopereira.eventsdemo.fanoutexchange.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Modelo de requisição para envio de eventos no padrão CloudEvents.
 */
public class CloudEventRequest {

    @Schema(description = "Nome da exchange para onde a mensagem será enviada", example = "notifications.fanout", required = true)
    private String exchangeName;

    @Schema(description = "Tipo do evento CloudEvent", example = "dev.fbiopereira.notification.sent", required = true)
    private String eventType;

    @Schema(description = "Conteúdo da mensagem em formato JSON", example = "{\"message\":\"Hello World!\"}", required = true)
    private String payload;

    @Schema(description = "Assunto do evento (opcional)", example = "user-notification")
    private String subject;

    // Getters e Setters
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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
