package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.controller;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto.OperacaoVidaPrevDto;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.producer.OperacaoVidaEPrevProducer;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.service.OperacaoVidaEPrevService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contratacoes")

@Api(value = "Micro Serviço de operações de contratações")
public class OperacaoVidaEPrevController {

    @Autowired
    private OperacaoVidaEPrevProducer operacaoVidaEPrevProducer;

    @Autowired
    OperacaoVidaEPrevService operacaoVidaEPrevService;

    @ApiOperation(value = "Realiza uma operação de contratação")
    @PostMapping("/vida")
    @ResponseStatus(HttpStatus.CREATED)
    public void produce(
            @RequestHeader(value = "access-token", required = false) String accessToken,
            @RequestHeader("codigo_parceiro") String codigoParceiro,
            @RequestBody  OperacaoVidaPrevDto operacaoVidaPrevDto) {
        operacaoVidaEPrevProducer.enviarMensagem(operacaoVidaEPrevService.buildOperacaoVidaPrev(operacaoVidaPrevDto, codigoParceiro));
    }
}