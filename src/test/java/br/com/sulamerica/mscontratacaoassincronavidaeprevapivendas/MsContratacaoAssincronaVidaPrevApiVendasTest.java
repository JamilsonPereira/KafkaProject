package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.producer.OperacaoVidaEPrevProducer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class MsContratacaoAssincronaVidaPrevApiVendasTest {
    @InjectMocks
    private MsContratacaoAssincronaVidaPrevApiVendas msContratacaoAssincronaVidaPrevApiVendas;

    @Test
    public void main() {
    Mockito.spy(MsContratacaoAssincronaVidaPrevApiVendas.class);

//        System.setProperty("spring.devtools.restart.enabled", "false");
      //  String[] args = {};
        //msContratacaoAssincronaVidaPrevApiVendas = new String()
     //  Mockito.verify(msContratacaoAssincronaVidaPrevApiVendas.getClass());
    }
}