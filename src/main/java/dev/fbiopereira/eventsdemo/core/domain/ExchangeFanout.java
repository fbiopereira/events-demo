package dev.fbiopereira.eventsdemo.core.domain;

import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventSchema;


import java.time.LocalDateTime;

public class ExchangeFanout {


    private String id;

    private String exchangeName;

    private boolean durable;

    private boolean autoDelete;

    private CloudEventSchema cloudEventSchema;

    private LocalDateTime createdAt;

    private LocalDateTime requestTimestamp;

    // Constructors
    public ExchangeFanout() {
        this.createdAt = LocalDateTime.now();
    }

    public ExchangeFanout(String id, String exchangeName, boolean durable, boolean autoDelete,
                                  CloudEventSchema cloudEventSchema, LocalDateTime requestTimestamp) {
        this();
        this.id = id;
        this.exchangeName = exchangeName;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.cloudEventSchema = cloudEventSchema;
        this.requestTimestamp = requestTimestamp;
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

    public boolean isDurable() {
        return durable;
    }

    public void setDurable(boolean durable) {
        this.durable = durable;
    }

    public boolean isAutoDelete() {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) {
        this.autoDelete = autoDelete;
    }

    public CloudEventSchema getCloudEventSchema() {
        return cloudEventSchema;
    }

    public void setCloudEventSchema(CloudEventSchema cloudEventSchema) {
        this.cloudEventSchema = cloudEventSchema;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(LocalDateTime requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

}
