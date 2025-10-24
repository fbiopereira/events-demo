package dev.fbiopereira.eventsdemo.core.usecase;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;

public interface CreateExchangeFanoutQueueUseCase {

    void createExchangeFanoutQueue(ExchangeFanoutQueue exchangeFanoutQueue);
}
