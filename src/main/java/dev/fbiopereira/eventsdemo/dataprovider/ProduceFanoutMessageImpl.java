package dev.fbiopereira.eventsdemo.dataprovider;


import dev.fbiopereira.eventsdemo.core.dataprovider.ProduceFanoutMessage;
import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;
import dev.fbiopereira.eventsdemo.dataprovider.mapper.ExchangeFanoutMessageEntityMapper;
import dev.fbiopereira.eventsdemo.dataprovider.mongodb.ExchangeFanoutMessageRepository;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.ExchangeFanoutRabbit;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.RabbitProducer;
import org.springframework.stereotype.Component;


@Component
public class ProduceFanoutMessageImpl implements ProduceFanoutMessage {

    private final ExchangeFanoutMessageRepository exchangeFanoutMessageRepository;

    private final RabbitProducer rabbitProducer;
    private final ExchangeFanoutMessageEntityMapper exchangeFanoutMessageMapper;



    public ProduceFanoutMessageImpl(ExchangeFanoutMessageRepository exchangeFanoutMessageRepository, ExchangeFanoutMessageEntityMapper exchangeFanoutMessageMapper, RabbitProducer rabbitProducer, ExchangeFanoutRabbit exchangeFanoutRabbit) {
        this.exchangeFanoutMessageRepository = exchangeFanoutMessageRepository;
        this.exchangeFanoutMessageMapper = exchangeFanoutMessageMapper;
        this.rabbitProducer = rabbitProducer;
    }

    @Override
    public void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage) {
        var exchangeFanoutMessageEntity = exchangeFanoutMessageMapper.toExchangeFanoutMessageEntity(exchangeFanoutMessage);
        var savedEntity = exchangeFanoutMessageRepository.save(exchangeFanoutMessageEntity);
        exchangeFanoutMessage.setId(savedEntity.getId());
        rabbitProducer.sendMessage(exchangeFanoutMessage);
    }
}
