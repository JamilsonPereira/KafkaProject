package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration;

import br.com.sulamerica.cofresenha.dto.Cofre;
import br.com.sulamerica.cofresenha.service.CofresenhaService;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration.properties.CofreSenhaProperties;
import org.apache.avro.generic.GenericRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import javax.security.auth.login.CredentialNotFoundException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

class KafkaClientConfigurationTest {


    @InjectMocks
    KafkaClientConfiguration kafkaClientConfiguration;

    @Mock
    private CofresenhaService cofresenhaService;

    @Mock
    private CofreSenhaProperties cofreSenhaProperties;

    @Mock
    private KafkaProperties kafkaProperties;


    @Mock
    ProducerFactory<String, GenericRecord> producerFactory;

    @Mock
    Cofre cofreCluster;

    @Mock
    Cofre cofreSchemaRegistry;


    private static final String SCHEMA_REGISTRY_AUTH_CONFIG_KEY = "basic.auth.user.info";

    private static final String CLUSTER_AUTH_CONFIG_VALUE = "org.apache.kafka.common.security.plain.PlainLoginModule   required username=\"%s\"    password=\"%s\";";

    private static final String CLUSTER_AUTH_CONFIG_KEY = "sasl.jaas.config";


    @BeforeEach
    void setUp() {
        initMocks(this);

        cofreSenhaProperties.setClusterKafka(13262);
        cofreSenhaProperties.setSchemaRegistryKafka(13252);
    }

    @Test
    void avroTemplate() {
        KafkaTemplate<String, GenericRecord> kafkaTemplate = kafkaClientConfiguration.avroTemplate(producerFactory);
        assertNotNull(kafkaTemplate);
        when(kafkaClientConfiguration.avroTemplate(producerFactory)).thenCallRealMethod();
    }

    @Test
    void init() throws CredentialNotFoundException {
        //TODO o Fluxo  de Configuração de Credencial, acredito que finalizar esse fluxo irá cobrir tbm o CofreSenhaProperties.
    }
}