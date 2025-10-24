package dev.fbiopereira.eventsdemo.dataprovider.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "ExchangeFannoutQueues")
public class ExchangeFanoutQueueEntity {

    @Id
    private String id;

    @Field("queue_name")
    private String queueName;

    @Field("exchange_name")
    private String exchangeName;

    @Field("durable")
    private boolean durable = true;

    @Field("auto_delete")
    private boolean autoDelete = false;

    @Field("exclusive")
    private boolean exclusive = false;

    @Field("created_at")
    private LocalDateTime createdAt;

    // Constructors

    public ExchangeFanoutQueueEntity() {
            }

    public ExchangeFanoutQueueEntity(String id, String queueName, String exchangeName, boolean durable, boolean autoDelete, boolean exclusive) {
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
