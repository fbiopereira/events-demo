package dev.fbiopereira.eventsdemo.fanoutexchange.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "events")
public class EventDocument {

    @Id
    private String id;

    @Field("exchange_name")
    private String exchangeName;

    @Field("event_type")
    private String eventType;

    @Field("subject")
    private String subject;

    @Field("payload")
    private String payload;

    @Field("event_id")
    private String eventId;

    @Field("received_at")
    private LocalDateTime receivedAt;

    @Field("sent_at")
    private LocalDateTime sentAt;

    @Field("status")
    private String status; // RECEIVED, SENT, ERROR

    // Constructors
    public EventDocument() {
        this.receivedAt = LocalDateTime.now();
        this.status = "RECEIVED";
    }

    public EventDocument(String exchangeName, String eventType, String subject, String payload) {
        this();
        this.exchangeName = exchangeName;
        this.eventType = eventType;
        this.subject = subject;
        this.payload = payload;
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

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
