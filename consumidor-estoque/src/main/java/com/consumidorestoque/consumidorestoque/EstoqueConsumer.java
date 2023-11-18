package com.consumidorestoque.consumidorestoque;

import org.librabbitmq.constants.RabbitMQConstants;
import org.librabbitmq.dto.EstoqueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    private static Logger logger = LoggerFactory.getLogger(EstoqueConsumer.class);

    @RabbitListener(queues = RabbitMQConstants.QUEUE_ESTOQUE)
    private void consumer(EstoqueDTO estoqueDTO) {
        logger.info("Estoque object received SUCCESSFULLY via queue: ".concat(estoqueDTO.toString()));
    }

}
