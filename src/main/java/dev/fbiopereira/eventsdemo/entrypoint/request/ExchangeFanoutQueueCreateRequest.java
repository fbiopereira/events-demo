package dev.fbiopereira.eventsdemo.entrypoint.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExchangeFanoutQueueCreateRequest {

    @Schema(description = "Nome da fila a ser criada", example = "notifications.email.queue", required = true)
    private String queueName;

    @Schema(description = "Nome da exchange fanout à qual a fila deve ser associada", example = "notifications.fanout", required = true)
    private String exchangeName;

    @Schema(description = "Se true, a fila sobrevive a reinicializações do broker", example = "true")
    private boolean durable = true;

    @Schema(description = "Se true, a fila é removida automaticamente quando o último consumidor se desconecta", example = "false")
    private boolean autoDelete = false;

    @Schema(description = "Se true, a fila só pode ser acessada pela conexão que a criou", example = "false")
    private boolean exclusive = false;

    // Getters e Setters
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

}
