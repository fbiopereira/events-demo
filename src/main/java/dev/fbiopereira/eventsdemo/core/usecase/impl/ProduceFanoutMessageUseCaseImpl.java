package dev.fbiopereira.eventsdemo.core.usecase.impl;


import dev.fbiopereira.eventsdemo.core.dataprovider.ProduceFanoutMessage;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;
import dev.fbiopereira.eventsdemo.core.usecase.ProduceFanoutMessageUseCase;

public class ProduceFanoutMessageUseCaseImpl implements ProduceFanoutMessageUseCase {

    private final ProduceFanoutMessage produceFanoutMessage;
    public ProduceFanoutMessageUseCaseImpl(ProduceFanoutMessage produceFanoutMessage) {
        this.produceFanoutMessage = produceFanoutMessage;
    }

    @Override
    public void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage) {

        produceFanoutMessage.sendMessage(exchangeFanoutMessage);

    }

}
