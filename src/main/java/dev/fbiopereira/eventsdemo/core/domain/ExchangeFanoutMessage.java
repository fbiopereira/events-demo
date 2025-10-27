package dev.fbiopereira.eventsdemo.core.domain;

import java.time.LocalDateTime;

public class ExchangeFanoutMessage {

    private String id;

    private String exchangeName;

    private String eventType;

    private String subject;

    private String routingKey;

    private String payload;

    private LocalDateTime createdAt;

    public ExchangeFanoutMessage() {
        this.createdAt = LocalDateTime.now();
    }

    public ExchangeFanoutMessage(String id, String exchangeName, String eventType, String payload, String subject, String routingKey) {
        this();
        this.id = id;
        this.exchangeName = exchangeName;
        this.eventType = eventType;
        this.subject = subject;
        this.routingKey = routingKey;
        this.payload = payload + " " + this.createdAt;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

}
