package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.producer;

import br.com.sulamerica.vep.eventos.schema.OperacaoVidaPrev;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class OperacaoVidaEPrevProducer {
    private static final Logger logger = LoggerFactory.getLogger(OperacaoVidaEPrevProducer.class);

    @Autowired
    private KafkaTemplate<String, GenericRecord> kafkaTemplate;

    @Value("${operacaovidaeprev.topic}")
    private String topic;

    public void enviarMensagem(OperacaoVidaPrev operacaoVidaPrev) {

        ListenableFuture<SendResult<String, GenericRecord>> future = kafkaTemplate.send(topic, operacaoVidaPrev);

        future.addCallback(new ListenableFutureCallback<SendResult<String, GenericRecord>>() {
            @Override
            public void onSuccess(SendResult<String, GenericRecord> stringOperacaoVidaPrevSendResult) {
                logger.info("Mensagem enviada com sucesso");
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Falha no envio da Mensagem");
            }
        });
    }
}