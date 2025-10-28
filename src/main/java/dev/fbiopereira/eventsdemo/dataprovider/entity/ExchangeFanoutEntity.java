package dev.fbiopereira.eventsdemo.dataprovider.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Document(collection = "ExchangeFannouts")
public class ExchangeFanoutEntity {

    @Id
    private String id;

    @Field("exchange_name")
    private String exchangeName;

    @Field("durable")
    private boolean durable;

    @Field("auto_delete")
    private boolean autoDelete;

    @Field("cloudevent_schema")
    private String cloudEventSchema;

    @Field("created_at")
    private LocalDateTime createdAt;


    // Constructors
    public ExchangeFanoutEntity() {
    }

    public ExchangeFanoutEntity(String id, String exchangeName, boolean durable, boolean autoDelete,
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
