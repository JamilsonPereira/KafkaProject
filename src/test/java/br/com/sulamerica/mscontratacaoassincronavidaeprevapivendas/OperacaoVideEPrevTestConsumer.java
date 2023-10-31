package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas;

import br.com.sulamerica.vep.eventos.schema.RespostaVidaPrev;

import lombok.Getter;
import lombok.Setter;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;


@Getter
@Setter
@Slf4j
@Component
public class OperacaoVideEPrevTestConsumer {
    private CountDownLatch latch;
    private RespostaVidaPrev resposta;

    @KafkaListener(topics = "${operacaovidaeprev.resultado-topic}", groupId = "resultado-vida-e-prev-test-consumer")
    public void receive(ConsumerRecord<String, RespostaVidaPrev> consumerRecord, Acknowledgment ack) {
        setResposta(consumerRecord.value());
        ack.acknowledge();
        latch.countDown();
    }

    private void setResposta(RespostaVidaPrev value) {

    }

}
