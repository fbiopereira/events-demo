package dev.fbiopereira.eventsdemo.fanoutexchange.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class FanoutExchangeRequest {

    @Schema(description = "Nome da exchange a ser criada", example = "notifications.fanout")
    private String exchangeName;

    @Schema(description = "Se true, a exchange sobrevive a reinicializações do broker", example = "true")
    private boolean durable;

    @Schema(description = "Se true, a exchange é removida quando não tem mais bindings", example = "false")
    private boolean autoDelete;

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
}
