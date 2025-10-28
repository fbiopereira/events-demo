package dev.fbiopereira.eventsdemo.dataprovider.mongodb;

import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeFanoutMessageRepository extends MongoRepository<ExchangeFanoutMessageEntity, String> {
}
