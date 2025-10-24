package dev.fbiopereira.eventsdemo.dataprovider.mapper;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutQueueEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutQueueEntityMapper {

   ExchangeFanoutQueueEntity toExchangeFanoutQueueEntity(ExchangeFanoutQueue exchangeFannoutQueue);

   ExchangeFanoutQueue toExchangeFanoutQueue(ExchangeFanoutQueueEntity exchangeFanoutEntityQueue);
}
