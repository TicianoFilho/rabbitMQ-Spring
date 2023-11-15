package br.study.estoquepreco.rest;

import br.study.estoquepreco.dto.EstoqueDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estoque")
public class PrecoResource {

    private ResponseEntity editEstoque(EstoqueDTO estoqueDTO) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
