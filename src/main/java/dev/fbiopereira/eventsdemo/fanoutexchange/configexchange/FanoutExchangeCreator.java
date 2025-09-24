package dev.fbiopereira.eventsdemo.fanoutexchange.configexchange;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutExchangeCreator {

    private final AmqpAdmin amqpAdmin;

    @Autowired
    public FanoutExchangeCreator(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    /**
     * Cria uma exchange do tipo fanout no RabbitMQ.
     *
     * @param exchangeName nome da exchange a ser criada
     * @param durable se true, a exchange sobrevive a reinicializações do broker
     * @param autoDelete se true, a exchange é removida quando não tem mais bindings
     * @return a instância da FanoutExchange criada
     */
    public FanoutExchange createFanoutExchange(String exchangeName, boolean durable, boolean autoDelete) {
        FanoutExchange fanoutExchange = new FanoutExchange(exchangeName, durable, autoDelete);
        amqpAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }
}
