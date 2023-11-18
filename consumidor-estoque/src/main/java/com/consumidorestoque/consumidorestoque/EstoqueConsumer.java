package com.consumidorestoque.consumidorestoque;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EstoqueConsumer {

    @RabbitListener(queues = "q.estoque")
    private void consumer() {

    }

}
