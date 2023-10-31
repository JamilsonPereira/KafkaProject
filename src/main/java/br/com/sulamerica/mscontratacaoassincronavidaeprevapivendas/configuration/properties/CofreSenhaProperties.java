package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.configuration.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cofresenha")
public class CofreSenhaProperties {

    private Integer clusterKafka;
    private Integer schemaRegistryKafka;

    public Integer getClusterKafka() {
        return clusterKafka;
    }

    public void setClusterKafka(Integer clusterKafka) {
        this.clusterKafka = clusterKafka;
    }

    public Integer getSchemaRegistryKafka() {
        return schemaRegistryKafka;
    }

    public void setSchemaRegistryKafka(Integer schemaRegistryKafka) {
        this.schemaRegistryKafka = schemaRegistryKafka;
    }
}
