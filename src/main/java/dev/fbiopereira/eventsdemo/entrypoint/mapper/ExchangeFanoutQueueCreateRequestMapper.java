package dev.fbiopereira.eventsdemo.entrypoint.mapper;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutQueue;
import dev.fbiopereira.eventsdemo.entrypoint.request.ExchangeFanoutQueueCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExchangeFanoutQueueCreateRequestMapper {

    @Mapping(target = "id", ignore = true)
    ExchangeFanoutQueue toExchangeFanoutQueue(ExchangeFanoutQueueCreateRequest exchangeFanoutQueueCreateRequest);

    ExchangeFanoutQueueCreateRequest toExchangeFanoutQueueCreateRequest(ExchangeFanoutQueue exchangeFanoutQueue);

}
