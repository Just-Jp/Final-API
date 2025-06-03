package com.example.demo.dto;

import com.example.demo.model.Endereco;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description="DTO que representa um endereço para uma inserção ou atualização")
public class EnderecoDTO {
    
    @Schema(description="CEP do endereço")
    private String cep;

    @Schema(description="Logradouro do endereço")
    private String logradouro;

    @Schema(description="Complemento do endereço")
    private String complemento;

    @Schema(description="Bairro do endereço")
    private String bairro;

    @Schema(description="Localidade do endereço")
    private String localidade;

    @Schema(description="Unidade Federativa do endereço")
    private String uf;

    public EnderecoDTO(Endereco endereco) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.uf = endereco.getUf();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
