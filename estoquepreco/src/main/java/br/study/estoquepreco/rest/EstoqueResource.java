package br.study.estoquepreco.rest;

import br.study.estoquepreco.service.SenderService;
import org.librabbitmq.constants.RabbitMQConstants;
import org.librabbitmq.dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estoque")
public class EstoqueResource {

    @Autowired
    SenderService senderService;


    @PutMapping
    private ResponseEntity editEstoque(@RequestBody EstoqueDTO estoqueDTO) throws Exception {
        this.senderService.sendMessage(RabbitMQConstants.QUEUE_ESTOQUE, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
