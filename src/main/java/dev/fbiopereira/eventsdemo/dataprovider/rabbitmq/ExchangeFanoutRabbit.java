package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import org.springframework.amqp.core.FanoutExchange;

public interface ExchangeFanoutRabbit {

    FanoutExchange create(ExchangeFanout exchangeFanout);

}

