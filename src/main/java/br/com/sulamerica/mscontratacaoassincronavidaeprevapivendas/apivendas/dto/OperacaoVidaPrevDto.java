package br.com.sulamerica.mscontratacaoassincronavidaeprevapivendas.apivendas.dto;

import br.com.sulamerica.vep.eventos.schema.Operacao;
import br.com.sulamerica.vep.eventos.schema.TipoSeguro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
public class OperacaoVidaPrevDto {

    @JsonProperty(value = "client_id")
    @NotNull(message = "Id do Cliente é obrigatório")
    private String clientId;

    @JsonProperty(value = "codigo_rastreio")
    @NotNull(message = "Codigo de Rastreio é obrigatório")
    private String codigoRastreio;

    @JsonProperty(value = "operacao")
    @NotNull(message = "Operacao é obrigatório")
    private Operacao operacao;

    @JsonProperty(value = "tipo_seguro")
    @NotNull(message = "Tipo Seguro é obrigatório")
    private TipoSeguro tipoSeguro;

    @JsonProperty(value = "dados_operacao")
    private String dadosOperacao;

    @JsonProperty(value = "url_callback")
    private String urlCallBack;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public String getDadosOperacao() {
        return dadosOperacao;
    }

    public void setDadosOperacao(String dadosOperacao) {
        this.dadosOperacao = dadosOperacao;
    }

    public String getUrlCallBack() {
        return urlCallBack;
    }

    public void setUrlCallBack(String urlCallBack) {
        this.urlCallBack = urlCallBack;
    }
}