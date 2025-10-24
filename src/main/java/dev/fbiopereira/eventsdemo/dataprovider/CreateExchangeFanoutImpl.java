package dev.fbiopereira.eventsdemo.dataprovider;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanout;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.dataprovider.mapper.ExchangeFanoutEntityMapper;
import dev.fbiopereira.eventsdemo.dataprovider.mongodb.ExchangeFanoutRepository;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.ExchangeFanoutRabbit;
import org.springframework.stereotype.Component;


@Component
public class CreateExchangeFanoutImpl implements CreateExchangeFanout {

    private final ExchangeFanoutRepository exchangeFanoutRepository;

    private final ExchangeFanoutEntityMapper exchangeFanoutMapper;

    private final ExchangeFanoutRabbit exchangeFanoutRabbit;

    public CreateExchangeFanoutImpl(ExchangeFanoutRepository exchangeFanoutRepository, ExchangeFanoutEntityMapper exchangeFanoutMapper, ExchangeFanoutRabbit exchangeFanoutRabbit) {
        this.exchangeFanoutRepository = exchangeFanoutRepository;
        this.exchangeFanoutMapper = exchangeFanoutMapper;
        this.exchangeFanoutRabbit = exchangeFanoutRabbit;
    }

    @Override
    public void create(ExchangeFanout exchangeFanout) {

        var exchangeFanoutEntity = exchangeFanoutMapper.toExchangeFanoutEntity(exchangeFanout);
        exchangeFanoutRepository.save(exchangeFanoutEntity);
        exchangeFanoutRabbit.create(exchangeFanout);
    }
}
