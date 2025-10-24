package dev.fbiopereira.eventsdemo.dataprovider.mongodb;

import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutQueueEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeFanoutQueueRepository extends MongoRepository<ExchangeFanoutQueueEntity, String> {
}
