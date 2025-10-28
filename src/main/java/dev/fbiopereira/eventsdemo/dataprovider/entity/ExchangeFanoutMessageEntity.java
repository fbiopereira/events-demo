package dev.fbiopereira.eventsdemo.dataprovider.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "ExchangeFannoutMessages")
public class ExchangeFanoutMessageEntity {

    @Id
    private String id;

    @Field("exchange_name")
    private String exchangeName;

    @Field("event_type")
    private String eventType;

    @Field("subject")
    private String subject;

    @Field("routing_key")
    private String routingKey;

    @Field("payload")
    private String payload;

    @Field("created_at")
    private LocalDateTime createdAt;


    // Constructors
    public ExchangeFanoutMessageEntity() {
    }

    public ExchangeFanoutMessageEntity(String id, String exchangeName, String eventType,
                                      String subject, String payload, LocalDateTime createdAt, String routingKey) {
        this();
        this.id = id;
        this.exchangeName = exchangeName;
        this.eventType = eventType;
        this.subject = subject;
        this.payload = payload;
        this.createdAt = createdAt;
        this.routingKey = routingKey;
    }

    // Getters and Setters


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
