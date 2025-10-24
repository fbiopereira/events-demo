package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.impl;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.ExchangeFanoutRabbit;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.stereotype.Component;

@Component
public class ExchangeFanoutRabbitImpl implements ExchangeFanoutRabbit {

    private final AmqpAdmin amqpAdmin;

    public ExchangeFanoutRabbitImpl(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @Override
    public FanoutExchange create(ExchangeFanout exchangeFanout) {

        FanoutExchange fanoutExchange = new FanoutExchange(exchangeFanout.getExchangeName(), exchangeFanout.isDurable(), exchangeFanout.isAutoDelete());
        amqpAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }
}
