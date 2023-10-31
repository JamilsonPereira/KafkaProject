package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class MsContratacaoAssincronaVidaPrevApiVendas {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MsContratacaoAssincronaVidaPrevApiVendas.class, args);
    }
}