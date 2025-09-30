package dev.fbiopereira.eventsdemo.fanoutexchange.document;

import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventSchema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "fannouts")
public class FanoutExchangeDocument {

    @Id
    private String id;

    @Field("exchange_name")
    private String exchangeName;

    @Field("durable")
    private boolean durable;

    @Field("auto_delete")
    private boolean autoDelete;

    @Field("cloudevent_schema")
    private CloudEventSchema cloudEventSchema;

    @Field("created_at")
    private LocalDateTime createdAt;

    @Field("request_timestamp")
    private LocalDateTime requestTimestamp;

    // Constructors
    public FanoutExchangeDocument() {
        this.createdAt = LocalDateTime.now();
    }

    public FanoutExchangeDocument(String exchangeName, boolean durable, boolean autoDelete,
                                  CloudEventSchema cloudEventSchema, LocalDateTime requestTimestamp) {
        this();
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
