package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;

public interface RabbitProducer {

    void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage);
}
