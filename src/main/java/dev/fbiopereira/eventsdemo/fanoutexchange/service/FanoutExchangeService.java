package dev.fbiopereira.eventsdemo.fanoutexchange.service;

import dev.fbiopereira.eventsdemo.fanoutexchange.document.FanoutExchangeDocument;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.FanoutExchangeRequest;
import dev.fbiopereira.eventsdemo.fanoutexchange.model.CloudEventSchema;
import dev.fbiopereira.eventsdemo.fanoutexchange.repository.FanoutExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FanoutExchangeService {

    private final FanoutExchangeRepository repository;

    @Autowired
    public FanoutExchangeService(FanoutExchangeRepository repository) {
        this.repository = repository;
    }

    /**
     * Salva os dados do fanout exchange no MongoDB
     */
    public FanoutExchangeDocument saveFanoutExchange(FanoutExchangeRequest request, LocalDateTime requestTimestamp) {

        // Valida o esquema CloudEvent básico
        validateCloudEventSchema(request.getCloudEventSchema());

        FanoutExchangeDocument document = new FanoutExchangeDocument(
            request.getExchangeName(),
            request.isDurable(),
            request.isAutoDelete(),
            request.getCloudEventSchema(),
            requestTimestamp
        );

        return repository.save(document);
    }

    /**
     * Validação básica do esquema CloudEvent 1.0
     */
    private void validateCloudEventSchema(CloudEventSchema schema) {
        if (schema == null) {
            throw new IllegalArgumentException("CloudEvent schema não pode ser nulo");
        }

        // Validação dos campos obrigatórios do CloudEvent 1.0
        if (schema.getSpecversion() == null || schema.getSpecversion().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obrigatório do CloudEvent ausente: specversion");
        }

        if (schema.getType() == null || schema.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obrigatório do CloudEvent ausente: type");
        }

        if (schema.getSource() == null || schema.getSource().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obrigatório do CloudEvent ausente: source");
        }

        if (schema.getId() == null || schema.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo obrigatório do CloudEvent ausente: id");
        }

        // Valida se a versão é 1.0
        if (!"1.0".equals(schema.getSpecversion())) {
            throw new IllegalArgumentException("Versão do CloudEvent deve ser 1.0, recebido: " + schema.getSpecversion());
        }

        // Valida se os dados estão presentes
        if (schema.getData() == null) {
            throw new IllegalArgumentException("Campo 'data' do CloudEvent é obrigatório");
        }

        if (schema.getData().getObjectId() == null || schema.getData().getObjectId().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo 'objectId' dentro de 'data' é obrigatório");
        }

        if (schema.getData().getObjectDetail() == null || schema.getData().getObjectDetail().trim().isEmpty()) {
            throw new IllegalArgumentException("Campo 'objectDetail' dentro de 'data' é obrigatório");
        }
    }

    /**
     * Busca uma exchange pelo nome
     */
    public FanoutExchangeDocument findByExchangeName(String exchangeName) {
        return repository.findByExchangeName(exchangeName).orElse(null);
    }

    /**
     * Lista todas as exchanges
     */
    public List<FanoutExchangeDocument> findAll() {
        return repository.findAll();
    }
}
