package dev.fbiopereira.eventsdemo.configuration;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanout;
import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.dataprovider.ProduceFanoutMessage;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutQueueUseCase;
import dev.fbiopereira.eventsdemo.core.usecase.CreateExchangeFanoutUseCase;
import dev.fbiopereira.eventsdemo.core.usecase.ProduceFanoutMessageUseCase;
import dev.fbiopereira.eventsdemo.core.usecase.impl.CreateExchangeFanoutQueueUseCaseImpl;
import dev.fbiopereira.eventsdemo.core.usecase.impl.CreateExchangeFanoutUseCaseImpl;
import dev.fbiopereira.eventsdemo.core.usecase.impl.ProduceFanoutMessageUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public CreateExchangeFanoutUseCase createExchangeFanoutUseCase(
            CreateExchangeFanout createExchangeFanout) {
        return new CreateExchangeFanoutUseCaseImpl(createExchangeFanout);
    }

    @Bean
    public CreateExchangeFanoutQueueUseCase createExchangeFanoutQueueUseCase(
            CreateExchangeFanoutQueue createExchangeFanoutQueue) {
        return new CreateExchangeFanoutQueueUseCaseImpl(createExchangeFanoutQueue);
    }

    @Bean
    public ProduceFanoutMessageUseCase produceFanoutMessageUseCase(ProduceFanoutMessage produceFanoutMessage) {
        return new ProduceFanoutMessageUseCaseImpl(produceFanoutMessage);
    }

}
