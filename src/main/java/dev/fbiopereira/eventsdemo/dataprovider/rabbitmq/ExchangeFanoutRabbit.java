package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;

public interface ExchangeFanoutRabbit {

    FanoutExchange createFanout(ExchangeFanout exchangeFanout);

    Queue createAndBindFanoutQueue(ExchangeFanoutQueue exchangeFanoutQueue);

}

