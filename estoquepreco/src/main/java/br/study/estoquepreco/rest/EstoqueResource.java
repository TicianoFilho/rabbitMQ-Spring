package br.study.estoquepreco.rest;

import br.study.estoquepreco.constants.RabbitMQConstants;
import br.study.estoquepreco.dto.EstoqueDTO;
import br.study.estoquepreco.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueResource {

    @Autowired
    SenderService senderService;

    private static Logger logger = LoggerFactory.getLogger(EstoqueResource.class);

   @PutMapping
    private ResponseEntity editEstoque(EstoqueDTO estoqueDTO) {
       try {
           logger.info(" --- sending message from estoque to rabbitMQ ---");
           this.senderService.sendMessage(RabbitMQConstants.QUEUE_ESTOQUE, estoqueDTO);
           logger.info(" --- sending message from estoque to rabbitMQ (SUCCESS) ---");
           return new ResponseEntity(HttpStatus.OK);
       } catch (Exception e) {
           logger.error(" --- sending message from estoque to rabbitMQ (FAILED) ---");
           e.getMessage();
       }
       return null;
    }
}
