package dev.fbiopereira.eventsdemo.fanoutexchange.repository;

import dev.fbiopereira.eventsdemo.fanoutexchange.document.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<EventDocument, String> {

    List<EventDocument> findByExchangeName(String exchangeName);

    List<EventDocument> findByReceivedAtBetween(LocalDateTime start, LocalDateTime end);

    List<EventDocument> findByStatus(String status);
}
