package dev.fbiopereira.eventsdemo.core.usecase.impl;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutQueueUseCase;

public class CreateExchangeFanoutQueueUseCaseimpl implements CreateExchangeFanoutQueueUseCase {


    private final CreateExchangeFanoutQueue createExchangeFanoutQueue;

    public CreateExchangeFanoutQueueUseCaseimpl(CreateExchangeFanoutQueue createExchangeFanoutQueue) {
        this.createExchangeFanoutQueue = createExchangeFanoutQueue;
    }

    @Override
    public void createExchangeFanoutQueue(ExchangeFanoutQueue exchangeFanoutQueue) {
        createExchangeFanoutQueue.create(exchangeFanoutQueue);
    }
}
