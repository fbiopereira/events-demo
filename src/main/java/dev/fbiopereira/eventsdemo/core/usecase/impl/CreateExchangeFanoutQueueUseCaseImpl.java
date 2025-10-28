package dev.fbiopereira.eventsdemo.core.usecase.impl;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutQueueUseCase;

public class CreateExchangeFanoutQueueUseCaseImpl implements CreateExchangeFanoutQueueUseCase {


    private final CreateExchangeFanoutQueue createExchangeFanoutQueue;

    public CreateExchangeFanoutQueueUseCaseImpl(CreateExchangeFanoutQueue createExchangeFanoutQueue) {
        this.createExchangeFanoutQueue = createExchangeFanoutQueue;
    }

    @Override
    public void createExchangeFanoutQueue(ExchangeFanoutQueue exchangeFanoutQueue) {
        createExchangeFanoutQueue.create(exchangeFanoutQueue);
    }
}
