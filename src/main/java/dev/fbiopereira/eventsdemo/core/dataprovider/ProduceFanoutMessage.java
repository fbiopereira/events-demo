package dev.fbiopereira.eventsdemo.core.dataprovider;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;

public interface ProduceFanoutMessage {

    void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage);
}
