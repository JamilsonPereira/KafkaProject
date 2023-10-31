package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.producer;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto.OperacaoVidaPrevDto;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.service.OperacaoVidaEPrevService;
import br.com.sulamerica.vep.eventos.schema.Operacao;
import br.com.sulamerica.vep.eventos.schema.OperacaoVidaPrev;
import br.com.sulamerica.vep.eventos.schema.TipoSeguro;
import org.apache.avro.generic.GenericRecord;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class OperacaoVidaEPrevProducerTest {

    @InjectMocks
    private OperacaoVidaEPrevProducer operacaoVidaEPrevProducer;

    @Mock
    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @Mock
    ListenableFuture<SendResult<String, GenericRecord>> future;

    @Mock
    OperacaoVidaPrev operacaoVidaPrev;

    @Value("${operacaovidaeprev.topic}")
    private String topic;

    @Mock
    OperacaoVidaPrevDto operacaoVidaPrevDto;

    @Before
    public void setUp(){
        initMocks(this);
        when(kafkaTemplate.send(any(String.class), any(OperacaoVidaPrev.class))).thenReturn(future);

        OperacaoVidaPrevDto operacaoVidaPrevDto2 = new OperacaoVidaPrevDto();
        operacaoVidaPrevDto2.setClientId("desenvolvimento");
        operacaoVidaPrevDto2.setCodigoRastreio("4674");
        operacaoVidaPrevDto2.setTipoSeguro(TipoSeguro.PREV);
        operacaoVidaPrevDto2.setOperacao(Operacao.CONTRATACAO_PREV);
        operacaoVidaPrevDto2.setDadosOperacao("abc");

        OperacaoVidaEPrevService operacaoVidaEPrevService = new OperacaoVidaEPrevService();

        OperacaoVidaPrev operacaoVidaPrev = operacaoVidaEPrevService.buildOperacaoVidaPrev(operacaoVidaPrevDto2, "123456");
    }

    @Test
    public void dadoChamaContratacaoVidaAoProducerDeveRetornarMensagem(){

        when(kafkaTemplate.send(topic, operacaoVidaPrev)).thenReturn(future);
        operacaoVidaEPrevProducer.enviarMensagem(operacaoVidaPrev);

        SendResult<String, GenericRecord> sendResult = new SendResult<>(null, null);
        ListenableFutureCallback<SendResult<String, GenericRecord>> callback = captureCallback();
        callback.onSuccess(sendResult);

        verify(kafkaTemplate).send(eq(topic), eq(operacaoVidaPrev));
        verify(future).addCallback(any(ListenableFutureCallback.class));

    }

    @Test
    public void dadoChamaContratacaoVidaAoProducerDeveDarFalha(){

        when(kafkaTemplate.send(topic, operacaoVidaPrev)).thenReturn(future);
        operacaoVidaEPrevProducer.enviarMensagem(operacaoVidaPrev);

        ListenableFutureCallback<SendResult<String, GenericRecord>> callback = captureCallback();
        callback.onFailure(new RuntimeException("Falha no envio da Mensagem"));

        verify(kafkaTemplate).send(eq(topic), eq(operacaoVidaPrev));
        verify(future).addCallback(any(ListenableFutureCallback.class));
    }

    private ListenableFutureCallback<SendResult<String, GenericRecord>> captureCallback() {
        ArgumentCaptor<ListenableFutureCallback> callbackCaptor = ArgumentCaptor.forClass(ListenableFutureCallback.class);
        verify(future).addCallback(callbackCaptor.capture());
        return callbackCaptor.getValue();
    }
}
