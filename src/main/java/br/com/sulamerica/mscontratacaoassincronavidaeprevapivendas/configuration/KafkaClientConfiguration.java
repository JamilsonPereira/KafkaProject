package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration;

import br.com.sulamerica.cofresenha.dto.Cofre;
import br.com.sulamerica.cofresenha.service.CofresenhaService;
import br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration.properties.CofreSenhaProperties;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.trim;

import javax.annotation.PostConstruct;
import javax.security.auth.login.CredentialNotFoundException;

@Configuration
@EnableKafka
@Profile("!test")
public class KafkaClientConfiguration {

    private static final String SCHEMA_REGISTRY_AUTH_CONFIG_KEY = "basic.auth.user.info";

    private static final String CLUSTER_AUTH_CONFIG_VALUE = "org.apache.kafka.common.security.plain.PlainLoginModule   required username=\"%s\"    password=\"%s\";";

    private static final String CLUSTER_AUTH_CONFIG_KEY = "sasl.jaas.config";

    @Autowired
    private CofresenhaService cofresenhaService;

    @Autowired
    private CofreSenhaProperties cofreSenhaProperties;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaTemplate<String, GenericRecord> avroTemplate(ProducerFactory<String, GenericRecord> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @PostConstruct
    public void init() throws CredentialNotFoundException {
        final Cofre cofreCluster = consultSafebox(cofreSenhaProperties.getClusterKafka());
        final Cofre cofreSchemaRegistry = consultSafebox(cofreSenhaProperties.getSchemaRegistryKafka());

        final String clusterAuthConfigKey = String.format(CLUSTER_AUTH_CONFIG_VALUE, trim(cofreCluster.getSenha().getUsername()), trim(cofreCluster.getSenha().getSenha()));
        final String schemaRegistryAuthConfigValue = String.join(":", trim(cofreSchemaRegistry.getSenha().getUsername()), trim(cofreSchemaRegistry.getSenha().getSenha()));

        kafkaProperties.getProperties().put(CLUSTER_AUTH_CONFIG_KEY, clusterAuthConfigKey);
        kafkaProperties.getProperties().put(SCHEMA_REGISTRY_AUTH_CONFIG_KEY, schemaRegistryAuthConfigValue);
    }

    private Cofre consultSafebox(Integer idSafebox) throws CredentialNotFoundException {
        final Cofre cofre = cofresenhaService.buscarSegredoCofre(idSafebox);
        if (cofre == null || cofre.getSenha() == null || StringUtils.isBlank(cofre.getSenha().getSenha())) {
            throw new CredentialNotFoundException("Credential Not Found - Id ["+ idSafebox +"]");
        }

        return cofre;
    }
}
