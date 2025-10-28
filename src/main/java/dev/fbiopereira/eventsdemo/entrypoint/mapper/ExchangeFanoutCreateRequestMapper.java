package dev.fbiopereira.eventsdemo.entrypoint.mapper;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutCreateRequestMapper {

    @Mapping(target = "id", ignore = true)
    ExchangeFanout toExchangeFanout(ExchangeFanoutCreateRequest exchangeFanoutCreateRequest);



}
