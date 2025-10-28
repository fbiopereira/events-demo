package dev.fbiopereira.eventsdemo.entrypoint.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ExchangeFanoutCreateRequest {


    @Schema(description = "Nome da exchange a ser criada", example = "notifications.fanout")
    private String exchangeName;

    @Schema(description = "Se true, a exchange sobrevive a reinicializações do broker", example = "true")
    private boolean durable;

    @Schema(description = "Se true, a exchange é removida quando não tem mais bindings", example = "false")
    private boolean autoDelete;

    @Schema(description = "Esquema JSON no formato CloudEvent 1.0 para validar as mensagens",
            example = """
                    {
                      "specversion": "1.0",
                      "type": "com.example.notification",
                      "source": "/myapp/notifications",
                      "id": "string",
                      "time": "2023-01-01T00:00:00Z",
                      "datacontenttype": "application/json",
                      "data": {
                        "objectId": "12345",
                        "objectDetail": "Detalhes do objeto processado"
                      }
                    }
                    """)
    @Valid
    @NotNull
    private String cloudEventSchema;

    // Getters e setters
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

}
