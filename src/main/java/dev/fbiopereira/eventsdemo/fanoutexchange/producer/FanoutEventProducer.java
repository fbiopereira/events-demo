package dev.fbiopereira.eventsdemo.fanoutexchange.producer;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.format.EventFormat;
import io.cloudevents.jackson.JsonFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Produtor de eventos para exchanges do tipo fanout, seguindo o padrão CloudEvents 1.0.
 */
@Component
public class FanoutEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(FanoutEventProducer.class);
    private static final EventFormat eventFormat = new JsonFormat();
    private static final String CLOUD_EVENTS_CONTENT_TYPE = "application/cloudevents+json";
    private static final String SOURCE_PREFIX = "dev.fbiopereira.eventsdemo/fanoutexchange";

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publica uma mensagem em uma exchange do tipo fanout seguindo o padrão CloudEvents 1.0.
     *
     * @param exchangeName Nome da exchange onde a mensagem será publicada
     * @param type Tipo do evento (ex: "com.example.event.created")
     * @param payload Conteúdo da mensagem em formato String
     * @param subject Assunto do evento (opcional)
     * @return ID do evento publicado
     */
    public String publishEvent(String exchangeName, String type, String payload, String subject) {
        String eventId = UUID.randomUUID().toString();

        // Cria o CloudEvent conforme especificação 1.0
        CloudEvent event = CloudEventBuilder.v1()
                .withId(eventId)
                .withType(type)
                .withSource(URI.create(SOURCE_PREFIX + "/" + exchangeName))
                .withTime(OffsetDateTime.now())
                .withSubject(subject)
                .withDataContentType("application/json")
                .withData(payload.getBytes(StandardCharsets.UTF_8))
                .build();

        // Serializa o evento para o formato JSON
        byte[] serializedEvent = eventFormat.serialize(event);

        // Define as propriedades da mensagem
        MessageProperties properties = new MessageProperties();
        properties.setContentType(CLOUD_EVENTS_CONTENT_TYPE);
        properties.setContentEncoding(StandardCharsets.UTF_8.name());

        // Cria a mensagem do RabbitMQ
        Message message = new Message(serializedEvent, properties);

        // Publica na exchange (routing key é ignorada para fanout exchanges)
        rabbitTemplate.send(exchangeName, "", message);

        logger.info("CloudEvent publicado na exchange '{}' com ID: {}", exchangeName, eventId);
        return eventId;
    }

    /**
     * Versão simplificada para publicação de eventos.
     *
     * @param exchangeName Nome da exchange onde a mensagem será publicada
     * @param type Tipo do evento
     * @param payload Conteúdo da mensagem
     * @return ID do evento publicado
     */
    public String publishEvent(String exchangeName, String type, String payload) {
        return publishEvent(exchangeName, type, payload, null);
    }
}
