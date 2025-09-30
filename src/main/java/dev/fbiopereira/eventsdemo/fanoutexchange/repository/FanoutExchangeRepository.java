package dev.fbiopereira.eventsdemo.fanoutexchange.repository;

import dev.fbiopereira.eventsdemo.fanoutexchange.document.FanoutExchangeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FanoutExchangeRepository extends MongoRepository<FanoutExchangeDocument, String> {

    Optional<FanoutExchangeDocument> findByExchangeName(String exchangeName);
}
