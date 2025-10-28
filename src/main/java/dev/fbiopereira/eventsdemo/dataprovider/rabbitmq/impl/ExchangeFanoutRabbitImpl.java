package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.impl;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.ExchangeFanoutRabbit;
import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

@Component
public class ExchangeFanoutRabbitImpl implements ExchangeFanoutRabbit {

    private final AmqpAdmin amqpAdmin;

    public ExchangeFanoutRabbitImpl(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    @Override
    public FanoutExchange createFanout(ExchangeFanout exchangeFanout) {

        FanoutExchange fanoutExchange = new FanoutExchange(exchangeFanout.getExchangeName(), exchangeFanout.isDurable(), exchangeFanout.isAutoDelete());
        amqpAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    @Override
    public Queue createAndBindFanoutQueue(ExchangeFanoutQueue exchangeFanoutQueue) {
        Queue queue = new Queue(exchangeFanoutQueue.getQueueName(), exchangeFanoutQueue.isDurable(), exchangeFanoutQueue.isExclusive(), exchangeFanoutQueue.isAutoDelete());
        amqpAdmin.declareQueue(queue);

        FanoutExchange fanoutExchange = new FanoutExchange(exchangeFanoutQueue.getExchangeName());
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        amqpAdmin.declareBinding(binding);

        return queue;
    }
}
