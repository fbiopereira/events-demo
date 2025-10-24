package dev.fbiopereira.eventsdemo.core.domain;

import java.time.LocalDateTime;

public class ExchangeFanoutQueue {

    private String id;
    private String queueName;
    private String exchangeName;
    private boolean durable = true;
    private boolean autoDelete = false;
    private boolean exclusive = false;
    private LocalDateTime createdAt;

    // Constructors

    public ExchangeFanoutQueue() {
        this.createdAt = LocalDateTime.now();
    }

    public ExchangeFanoutQueue(String id, String queueName, String exchangeName, boolean durable, boolean autoDelete, boolean exclusive) {
        this();
        this.id = id;
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.exclusive = exclusive;
    }


    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
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

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
