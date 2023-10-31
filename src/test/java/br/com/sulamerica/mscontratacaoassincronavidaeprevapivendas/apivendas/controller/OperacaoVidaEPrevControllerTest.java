package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.controller;

import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto.OperacaoVidaPrevDto;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.producer.OperacaoVidaEPrevProducer;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.service.OperacaoVidaEPrevService;
import br.com.sulamerica.vep.eventos.schema.Operacao;
import br.com.sulamerica.vep.eventos.schema.TipoSeguro;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
class OperacaoVidaEPrevControllerTest {

    @InjectMocks
    private OperacaoVidaEPrevController operacaoVidaEPrevController;

    @Mock
    private OperacaoVidaPrevDto operacaoVidaPrevDto;

    @Mock
    private OperacaoVidaEPrevProducer operacaoVidaEPrevProducer;

    @Mock
    private OperacaoVidaEPrevService operacaoVidaEPrevService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String codigo_parceiro = "123";

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(operacaoVidaEPrevController)
                .alwaysDo(print())
                .build();
        operacaoVidaEPrevController = Mockito.mock(OperacaoVidaEPrevController.class);
        objectMapper = new ObjectMapper();

    }

    @Test
    public void aoRealizarOperacaoDeContratacaoDeveSerOk() throws Exception {

        OperacaoVidaPrevDto operacaoVidaPrevDto2 = new OperacaoVidaPrevDto();
        operacaoVidaPrevDto2.setClientId("desenvolvimento");
        operacaoVidaPrevDto2.setCodigoRastreio("4674");
        operacaoVidaPrevDto2.setTipoSeguro(TipoSeguro.PREV);
        operacaoVidaPrevDto2.setOperacao(Operacao.CONTRATACAO_PREV);
        operacaoVidaPrevDto2.setDadosOperacao("abc");

        mockMvc.perform(post("/contratacoes/vida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("codigo_parceiro", codigo_parceiro)
                        .content(objectMapper.writeValueAsString(operacaoVidaPrevDto2))
                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}