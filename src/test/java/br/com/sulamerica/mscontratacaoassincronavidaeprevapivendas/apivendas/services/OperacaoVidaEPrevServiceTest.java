package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.services;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto.OperacaoVidaPrevDto;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.service.OperacaoVidaEPrevService;
import br.com.sulamerica.vep.eventos.schema.Operacao;
import br.com.sulamerica.vep.eventos.schema.OperacaoVidaPrev;
import br.com.sulamerica.vep.eventos.schema.TipoSeguro;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class )
@ContextConfiguration(classes = OperacaoVidaEPrevService.class)
public class OperacaoVidaEPrevServiceTest {
    final static String CODIGO_PARCEIRO = "123456";

    @Autowired
    private OperacaoVidaEPrevService operacaoVidaEPrevService;

    @Test
    public void dadoChamadaAoServicoDeOperacaoDoTipoVidaDeveRetornarUmObjetoDoTipoOperacaoVidaPrev() throws NoSuchFieldException, IllegalAccessException {

        final OperacaoVidaPrevDto operacaoVidaPrevDto = new OperacaoVidaPrevDto();
        operacaoVidaPrevDto.setOperacao(Operacao.CONTRATACAO);
        operacaoVidaPrevDto.setClientId("1");
        operacaoVidaPrevDto.setTipoSeguro(TipoSeguro.VIDA);
        operacaoVidaPrevDto.setOperacao(Operacao.CONTRATACAO);
        operacaoVidaPrevDto.setCodigoRastreio("12345");
        operacaoVidaPrevDto.setUrlCallBack("http:url/test/callback");
        operacaoVidaPrevDto.setDadosOperacao("DADOS_OPERACAO");

        final OperacaoVidaPrev operacaoVidaPrev = operacaoVidaEPrevService.buildOperacaoVidaPrev(operacaoVidaPrevDto, CODIGO_PARCEIRO);

        Assert.assertThat(operacaoVidaPrev.getOperacao(), Matchers.equalTo(Operacao.CONTRATACAO));
        Assert.assertThat(operacaoVidaPrev.getClientId(), Matchers.equalTo("1"));
        Assert.assertThat(operacaoVidaPrev.getTipoSeguro(), Matchers.equalTo(TipoSeguro.VIDA));
        Assert.assertThat(operacaoVidaPrev.getOperacao(), Matchers.equalTo(Operacao.CONTRATACAO));
        Assert.assertThat(operacaoVidaPrev.getCodigoRastreio(), Matchers.equalTo("12345"));
        Assert.assertThat(operacaoVidaPrev.getUrlCallback(), Matchers.equalTo("http:url/test/callback"));
        Assert.assertThat(operacaoVidaPrev.getCodigoParceiro(), Matchers.equalTo("123456"));
    }

    @Test
    public void dadoChamadaAoServicoDeOperacaoDoTipoPrevDeveRetornarUmObjetoDoTipoOperacaoVidaPrev() throws NoSuchFieldException, IllegalAccessException {

        final OperacaoVidaPrevDto operacaoVidaPrevDto = new OperacaoVidaPrevDto("1", "12345",Operacao.CONTRATACAO,TipoSeguro.PREV, "DADOS_OPERACAO","http:url/test/callback");
        final OperacaoVidaPrev operacaoVidaPrev = operacaoVidaEPrevService.buildOperacaoVidaPrev(operacaoVidaPrevDto, CODIGO_PARCEIRO);

        Assert.assertThat(operacaoVidaPrev.getOperacao(), Matchers.equalTo(Operacao.CONTRATACAO));
        Assert.assertThat(operacaoVidaPrev.getClientId(), Matchers.equalTo("1"));
        Assert.assertThat(operacaoVidaPrev.getTipoSeguro(), Matchers.equalTo(TipoSeguro.PREV));
        Assert.assertThat(operacaoVidaPrev.getOperacao(), Matchers.equalTo(Operacao.CONTRATACAO));
        Assert.assertThat(operacaoVidaPrev.getCodigoRastreio(), Matchers.equalTo("12345"));
        Assert.assertThat(operacaoVidaPrev.getUrlCallback(), Matchers.equalTo("http:url/test/callback"));
        Assert.assertThat(operacaoVidaPrev.getCodigoParceiro(), Matchers.equalTo("123456"));
    }
}
