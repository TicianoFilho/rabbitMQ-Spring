package br.study.estoquepreco.connections;

import br.study.estoquepreco.constants.RabbitMQConstants;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConnection {

    private static final String EXCHANGE_NAME = "amq.direct";

    private AmqpAdmin amqpAdmin;


    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue createQueue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange DirectExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    private Binding doBinding(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    // create and add a queues
    @PostConstruct
    private void addQueue() {
        Queue queueEstoque = this.createQueue(RabbitMQConstants.QUEUE_ESTOQUE);
        Queue queuePreco = this.createQueue(RabbitMQConstants.QUEUE_PRECO);

        DirectExchange exchange = this.DirectExchange();

        Binding estoqueBinding = this.doBinding(queueEstoque, exchange);
        Binding precoBinding = this.doBinding(queuePreco, exchange);

        // creating queues in RabbitMQ
        this.amqpAdmin.declareQueue(queueEstoque);
        this.amqpAdmin.declareQueue(queuePreco);

        // creating exchange
        this.amqpAdmin.declareExchange(exchange);

        // creating link
        this.amqpAdmin.declareBinding(estoqueBinding);
        this.amqpAdmin.declareBinding(precoBinding);

    }
}
