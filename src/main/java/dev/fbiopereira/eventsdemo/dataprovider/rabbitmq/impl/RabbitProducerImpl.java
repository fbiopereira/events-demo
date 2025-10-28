package dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.impl;

import dev.fbiopereira.eventsdemo.core.domain.ExchangeFanoutMessage;
import dev.fbiopereira.eventsdemo.dataprovider.rabbitmq.RabbitProducer;
import io.cloudevents.CloudEvent;
import io.cloudevents.core.builder.CloudEventBuilder;
import io.cloudevents.core.format.EventFormat;
import io.cloudevents.jackson.JsonFormat;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class RabbitProducerImpl implements RabbitProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final EventFormat eventFormat = new JsonFormat();

    public RabbitProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(ExchangeFanoutMessage exchangeFanoutMessage) {

        // Cria o CloudEvent conforme especificação 1.0
        CloudEvent event = CloudEventBuilder.v1()
                .withId(exchangeFanoutMessage.getId())
                .withType(exchangeFanoutMessage.getEventType())
                .withSource(URI.create("dev.fbiopereira.eventsdemo" + "/" + exchangeFanoutMessage.getExchangeName()))
                .withTime(exchangeFanoutMessage.getCreatedAt().atOffset(ZoneOffset.UTC))
                .withSubject(exchangeFanoutMessage.getSubject())
                .withDataContentType("application/json")
                .withData(exchangeFanoutMessage.getPayload().getBytes(StandardCharsets.UTF_8))
                .build();

        // Serializa o CloudEvent para JSON
        byte[] serializedEvent = eventFormat.serialize(event);

        // Define as propriedades da mensagem
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/cloudevents+json");
        properties.setContentEncoding(StandardCharsets.UTF_8.name());

        // Cria a mensagem do RabbitMQ
        Message message = new Message(serializedEvent, properties);

        // Publica na exchange
        rabbitTemplate.send(exchangeFanoutMessage.getExchangeName(), exchangeFanoutMessage.getRoutingKey(), message);

    }

}
