package dev.fbiopereira.eventsdemo.dataprovider;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.dataprovider.mapper.ExchangeFanoutQueueEntityMapper;
import dev.fbiopereira.eventsdemo.dataprovider.mongodb.ExchangeFanoutQueueRepository;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.ExchangeFanoutRabbit;
import org.springframework.stereotype.Component;


@Component
public class CreateExchangeFanoutQueueImpl implements CreateExchangeFanoutQueue {

    private final ExchangeFanoutQueueRepository exchangeFanoutQueueRepository;

    private final ExchangeFanoutQueueEntityMapper exchangeFanoutQueueMapper;

    private final ExchangeFanoutRabbit exchangeFanoutRabbit;

    public CreateExchangeFanoutQueueImpl(ExchangeFanoutQueueRepository exchangeFanoutQueueRepository, ExchangeFanoutQueueEntityMapper exchangeFanoutQueueMapper, ExchangeFanoutRabbit exchangeFanoutRabbit) {
        this.exchangeFanoutQueueRepository = exchangeFanoutQueueRepository;
        this.exchangeFanoutQueueMapper = exchangeFanoutQueueMapper;
        this.exchangeFanoutRabbit = exchangeFanoutRabbit;
    }

    @Override
    public void create(ExchangeFanoutQueue exchangeFanoutQueue) {

        var exchangeFanoutQueueEntity = exchangeFanoutQueueMapper.toExchangeFanoutQueueEntity(exchangeFanoutQueue);
        exchangeFanoutQueueRepository.save(exchangeFanoutQueueEntity);
        exchangeFanoutRabbit.createAndBindFanoutQueue(exchangeFanoutQueue);
    }
}
