package com.desafiosea.clientes_backend.viacep;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class ViaCepResponse {
    @JsonAlias("cep")
    private String cep;
    @JsonAlias("logradouro")
    private String logradouro;
    @JsonAlias("bairro")
    private String bairro;
    @JsonAlias("localidade")
    private String cidade;
    @JsonAlias("uf")
    private String uf;
}