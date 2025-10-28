package dev.fbiopereira.eventsdemo.core.usecase;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;

public interface ProduceFanoutMessageUseCase {

    void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage);
}
