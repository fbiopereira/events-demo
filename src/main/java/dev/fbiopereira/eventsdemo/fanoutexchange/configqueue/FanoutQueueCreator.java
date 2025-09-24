package dev.fbiopereira.eventsdemo.fanoutexchange.configqueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por criar filas e associá-las a exchanges fanout.
 */
@Component
public class FanoutQueueCreator {

    private static final Logger logger = LoggerFactory.getLogger(FanoutQueueCreator.class);

    private final AmqpAdmin amqpAdmin;

    @Autowired
    public FanoutQueueCreator(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    /**
     * Cria uma fila e a associa a uma exchange fanout existente.
     *
     * @param queueName nome da fila a ser criada
     * @param exchangeName nome da exchange fanout à qual a fila deve ser associada
     * @param durable se true, a fila sobrevive a reinicializações do broker
     * @param autoDelete se true, a fila é removida automaticamente quando o último consumidor se desconecta
     * @param exclusive se true, a fila só pode ser acessada pela conexão que a criou
     * @return a instância da Queue criada
     */
    public Queue createAndBindQueue(String queueName, String exchangeName,
                                   boolean durable, boolean autoDelete, boolean exclusive) {
        // Verifica se os parâmetros são válidos
        if (queueName == null || queueName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da fila não pode ser vazio");
        }

        if (exchangeName == null || exchangeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da exchange não pode ser vazio");
        }

        // Cria a fila
        Queue queue = new Queue(queueName, durable, exclusive, autoDelete);
        amqpAdmin.declareQueue(queue);
        logger.info("Fila '{}' criada com sucesso", queueName);

        // Referencia a exchange fanout existente (sem criar uma nova)
        FanoutExchange fanoutExchange = new FanoutExchange(exchangeName);

        // Cria binding entre a fila e a exchange
        // Observação: Em exchanges fanout, a routing key é ignorada
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        amqpAdmin.declareBinding(binding);

        logger.info("Binding entre fila '{}' e exchange '{}' criado com sucesso", queueName, exchangeName);

        return queue;
    }

    /**
     * Método simplificado para criar uma fila durável e não-exclusiva associada a uma exchange fanout.
     *
     * @param queueName nome da fila a ser criada
     * @param exchangeName nome da exchange fanout à qual a fila deve ser associada
     * @return a instância da Queue criada
     */
    public Queue createAndBindQueue(String queueName, String exchangeName) {
        return createAndBindQueue(queueName, exchangeName, true, false, false);
    }
}
