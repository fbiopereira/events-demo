package dev.fbiopereira.eventsdemo.dataprovider;

import dev.fbiopereira.eventsdemo.core.dataprovider.CreateExchangeFanout;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.dataprovider.mapper.ExchangeFanoutEntityMapper;
import dev.fbiopereira.eventsdemo.dataprovider.mongodb.ExchangeFanoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateExchangeFanoutImpl implements CreateExchangeFanout {

    @Autowired
    private ExchangeFanoutRepository exchangeFanoutRepository;

    @Autowired
    private ExchangeFanoutEntityMapper exchangeFanoutMapper;

    @Override
    public void create(ExchangeFanout exchangeFanout) {

        var exchangeFanoutEntity = exchangeFanoutMapper.toExchangeFanoutEntity(exchangeFanout);
        exchangeFanoutRepository.save(exchangeFanoutEntity);

    }
}
