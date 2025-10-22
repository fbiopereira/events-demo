package dev.fbiopereira.eventsdemo.core.usecase.impl;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanout;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutUseCase;

public class CreateExchangeFanoutUseCaseImpl implements CreateExchangeFanoutUseCase {

    private final CreateExchangeFanout createExchangeFanout;

    public CreateExchangeFanoutUseCaseImpl(CreateExchangeFanout createExchangeFanout) {
        this.createExchangeFanout = createExchangeFanout;
    }

    @Override
    public void createExchangeFanout(ExchangeFanout exchangeFanout) {
        createExchangeFanout.create(exchangeFanout);
    }
}
