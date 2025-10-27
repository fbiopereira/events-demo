package dev.fbiopereira.eventsdemo.dataprovider.mapper;


import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;
import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutMessageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutMessageEntityMapper {

   ExchangeFanoutMessageEntity toExchangeFanoutMessageEntity(ExchangeFanoutMessage exchangeFannoutMessage);

   ExchangeFanoutMessage toExchangeFanoutMessage(ExchangeFanoutMessageEntity exchangeFanoutMessageEntity);
}
