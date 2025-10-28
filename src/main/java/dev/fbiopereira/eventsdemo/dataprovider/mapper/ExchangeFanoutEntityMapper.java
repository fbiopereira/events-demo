package dev.fbiopereira.eventsdemo.dataprovider.mapper;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutEntityMapper {

   ExchangeFanoutEntity toExchangeFanoutEntity(ExchangeFanout exchangeFannout);

   ExchangeFanout toExchangeFanout(ExchangeFanoutEntity exchangeFanoutEntity);
}
