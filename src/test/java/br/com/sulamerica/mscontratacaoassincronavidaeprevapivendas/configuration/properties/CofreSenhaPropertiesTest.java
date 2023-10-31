package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration.properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.*;

@ConfigurationProperties(prefix = "cofresenha")
class CofreSenhaPropertiesTest {
    private Integer clusterKafka;
    private Integer schemaRegistryKafka;
    @Mock
    private CofreSenhaProperties cofreSenhaProperties;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testGetSetClusterKafka() {
        when(cofreSenhaProperties.getClusterKafka()).thenCallRealMethod();
    }
}