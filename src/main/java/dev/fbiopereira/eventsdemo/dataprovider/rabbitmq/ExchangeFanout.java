package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq;

import org.springframework.amqp.core.FanoutExchange;

public interface ExchangeFanout {

    FanoutExchange create(ExchangeFanout exchangeFanout);

}
