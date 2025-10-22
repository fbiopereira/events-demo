package dev.fbiopereira.eventsdemo.fanoutexchange.repository;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ExchangeFanoutRepository extends MongoRepository<ExchangeFanout, String> {
}
