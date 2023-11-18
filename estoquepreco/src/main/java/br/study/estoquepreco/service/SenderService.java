package br.study.estoquepreco.service;

import org.librabbitmq.dto.EstoqueDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static Logger logger = LoggerFactory.getLogger(SenderService.class);

    public void sendMessage(String queueName, Object message) throws Exception {
        try {
            logger.info(" --- sending message to rabbitMQ ---");
            this.rabbitTemplate.convertAndSend(queueName, message);
            logger.info(" --- sending message to rabbitMQ (SUCCESS) ---: " + ((message instanceof EstoqueDTO) ? ((EstoqueDTO)message).toString() : null));
        } catch (Exception e) {
            logger.error(" --- sending message to rabbitMQ (FAILED) ---");
            throw new Exception(e.getMessage());
        }
    }
}
