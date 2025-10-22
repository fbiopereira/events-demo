package dev.fbiopereira.eventsdemo.dataprovider.mongodb;

import dev.fbiopereira.eventsdemo.dataprovider.entity.ExchangeFanoutEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeFanoutRepository  extends MongoRepository<ExchangeFanoutEntity, String> {
}
