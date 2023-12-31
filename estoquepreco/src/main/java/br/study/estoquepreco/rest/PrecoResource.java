package br.study.estoquepreco.rest;

import br.study.estoquepreco.service.SenderService;
import org.librabbitmq.constants.RabbitMQConstants;
import org.librabbitmq.dto.PrecoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/preco")
public class PrecoResource {

    @Autowired
    SenderService senderService;

    private static Logger logger = LoggerFactory.getLogger(PrecoResource.class);
    @PutMapping
    private ResponseEntity editPreco(PrecoDTO precoDTO) {
        try {
            logger.info(" --- sending message from peco to rabbitMQ ---");
            this.senderService.sendMessage(RabbitMQConstants.QUEUE_PRECO, precoDTO);
            logger.info(" --- sending message from preco to rabbitMQ (SUCCESS) ---");
            return new ResponseEntity(HttpStatus.OK);
        } catch(Exception e) {
            logger.error(" --- sending message from preco to rabbitMQ (FAILED) ---");
            e.getMessage();
        }
        return null;
    }
}
