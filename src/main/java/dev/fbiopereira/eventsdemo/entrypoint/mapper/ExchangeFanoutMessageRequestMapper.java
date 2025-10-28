package dev.fbiopereira.eventsdemo.entrypoint.mapper;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutMessageRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutMessageRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ExchangeFanoutMessage toExchangeFanoutMessage(ExchangeFanoutMessageRequest exchangeFanoutMessageRequest);



}
