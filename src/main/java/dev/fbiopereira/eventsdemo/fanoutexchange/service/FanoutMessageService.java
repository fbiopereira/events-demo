package dev.fbiopereira.eventsdemo.fanoutexchange.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.fbiopereira.eventsdemo.fanoutexchange.document.EventDocument;
import dev.fbiopereira.eventsdemo.fanoutexchange.document.FanoutExchangeDocument;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventRequest;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventSchema;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.EventData;
import dev.fbiopereira.eventsdemo.fanoutexchange.producer.FanoutEventProducer;
import dev.fbiopereira.eventsdemo.fanoutexchange.repository.EventRepository;
import dev.fbiopereira.eventsdemo.fanoutexchange.repository.FanoutExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FanoutMessageService {

    private final FanoutEventProducer eventProducer;
    private final FanoutExchangeRepository fanoutExchangeRepository;
    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public FanoutMessageService(FanoutEventProducer eventProducer,
                               FanoutExchangeRepository fanoutExchangeRepository,
                               EventRepository eventRepository,
                               ObjectMapper objectMapper) {
        this.eventProducer = eventProducer;
        this.fanoutExchangeRepository = fanoutExchangeRepository;
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Valida e publica uma mensagem CloudEvent na exchange fanout especificada
     */
    public String validateAndPublishMessage(CloudEventRequest request) {
        // Validação básica dos campos obrigatórios
        validateBasicFields(request);

        // Buscar a configuração da exchange
        Optional<FanoutExchangeDocument> exchangeDoc = fanoutExchangeRepository.findByExchangeName(request.getExchangeName());
        if (exchangeDoc.isEmpty()) {
            throw new IllegalArgumentException("Exchange '" + request.getExchangeName() + "' não encontrada");
        }

        // Validar mensagem contra o esquema da exchange
        validateMessageAgainstSchema(request, exchangeDoc.get().getCloudEventSchema());

        // Salvar o evento no MongoDB antes do envio
        EventDocument eventDoc = new EventDocument(
                request.getExchangeName(),
                request.getEventType(),
                request.getSubject(),
                request.getPayload()
        );
        eventDoc = eventRepository.save(eventDoc);

        try {
            // Publicar o evento
            String eventId = eventProducer.publishEvent(
                    request.getExchangeName(),
                    request.getEventType(),
                    request.getPayload(),
                    request.getSubject()
            );

            // Atualizar o documento com o eventId e status de sucesso
            eventDoc.setEventId(eventId);
            eventDoc.setSentAt(LocalDateTime.now());
            eventDoc.setStatus("SENT");
            eventRepository.save(eventDoc);

            return eventId;
        } catch (Exception e) {
            // Atualizar o documento com status de erro
            eventDoc.setStatus("ERROR");
            eventRepository.save(eventDoc);
            throw e;
        }
    }

    private void validateBasicFields(CloudEventRequest request) {
        if (request.getExchangeName() == null || request.getExchangeName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da exchange não pode ser vazio");
        }
        if (request.getEventType() == null || request.getEventType().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo do evento não pode ser vazio");
        }
        if (request.getPayload() == null || request.getPayload().trim().isEmpty()) {
            throw new IllegalArgumentException("Payload não pode ser nulo ou vazio");
        }
    }

    private void validateMessageAgainstSchema(CloudEventRequest request, CloudEventSchema schema) throws Exception {
        if (schema == null) {
            // Se não há schema definido, não precisa validar
            return;
        }

        // Parse do payload para validar estrutura
        JsonNode payloadNode;
        try {
            payloadNode = objectMapper.readTree(request.getPayload());
        } catch (Exception e) {
            throw new IllegalArgumentException("Payload deve ser um JSON válido: " + e.getMessage());
        }

        // Validar tipo do evento se especificado no schema
        if (schema.getType() != null && !schema.getType().equals(request.getEventType())) {
            throw new IllegalArgumentException("Tipo do evento '" + request.getEventType() +
                    "' não corresponde ao esperado pelo schema: '" + schema.getType() + "'");
        }

        // Validar estrutura do data se especificado no schema
        if (schema.getData() != null) {
            validateDataStructure(payloadNode, schema.getData());
        }
    }

    private void validateDataStructure(JsonNode payloadNode, EventData expectedData) {
        // Verificar se o payload contém a estrutura de data esperada
        JsonNode dataNode = payloadNode.get("data");
        if (dataNode == null) {
            throw new IllegalArgumentException("Payload deve conter o campo 'data'");
        }

        // Validar objectId se especificado
        if (expectedData.getObjectId() != null) {
            JsonNode objectIdNode = dataNode.get("objectId");
            if (objectIdNode == null || !objectIdNode.isTextual()) {
                throw new IllegalArgumentException("Campo 'data.objectId' é obrigatório e deve ser string");
            }
        }

        // Validar objectDetail se especificado
        if (expectedData.getObjectDetail() != null) {
            JsonNode objectDetailNode = dataNode.get("objectDetail");
            if (objectDetailNode == null || !objectDetailNode.isTextual()) {
                throw new IllegalArgumentException("Campo 'data.objectDetail' é obrigatório e deve ser string");
            }
        }

        // Verificar se não há campos extras não permitidos (validação rigorosa)
        dataNode.fieldNames().forEachRemaining(fieldName -> {
            if (!"objectId".equals(fieldName) && !"objectDetail".equals(fieldName)) {
                throw new IllegalArgumentException("Campo não permitido no data: '" + fieldName + "'. " +
                        "Apenas 'objectId' e 'objectDetail' são permitidos");
            }
        });
    }
}
