package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.service;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto.OperacaoVidaPrevDto;
import br.com.sulamerica.vep.eventos.schema.OperacaoVidaPrev;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OperacaoVidaEPrevService {

    private static final Logger Logger = LoggerFactory.getLogger(OperacaoVidaEPrevService.class);

    @Value("${operacaovidaeprev.topic}")
    private String topic;

    public OperacaoVidaPrev buildOperacaoVidaPrev(OperacaoVidaPrevDto operacaoVidaPrevDto,  String codigoParceiro) {
        String dadosOperacao = operacaoVidaPrevDto.getDadosOperacao();

            OperacaoVidaPrev operacaoVidaPrev = OperacaoVidaPrev.newBuilder()
                    .setClientId(operacaoVidaPrevDto.getClientId())
                    .setTipoSeguro(operacaoVidaPrevDto.getTipoSeguro())
                    .setOperacao(operacaoVidaPrevDto.getOperacao())
                    .setCodigoRastreio(operacaoVidaPrevDto.getCodigoRastreio())
                    .setCodigoParceiro(codigoParceiro)
                    .setUrlCallback(operacaoVidaPrevDto.getUrlCallBack())
                    .setDadosOperacao(dadosOperacao)
                    .build();
            Logger.info("Enviando mensagem para o topico: ", topic );
            return operacaoVidaPrev;
    }
}
