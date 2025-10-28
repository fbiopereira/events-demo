package dev.fbiopereira.eventsdemo.core.domain;

import java.time.LocalDateTime;

public class ExchangeFanout {


    private String id;

    private String exchangeName;

    private boolean durable;

    private boolean autoDelete;

    private String cloudEventSchema;

    private LocalDateTime createdAt;

    // Constructors
    public ExchangeFanout() {
        this.createdAt = LocalDateTime.now();
    }

    public ExchangeFanout(String id, String exchangeName, boolean durable, boolean autoDelete,
                                  String cloudEventSchema) {
        this();
        this.id = id;
        this.exchangeName = exchangeName;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.cloudEventSchema = cloudEventSchema;

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

    public String getCloudEventSchema() {
        return cloudEventSchema;
    }

    public void setCloudEventSchema(String cloudEventSchema) {
        this.cloudEventSchema = cloudEventSchema;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
