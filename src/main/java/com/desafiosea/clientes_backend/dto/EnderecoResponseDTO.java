package com.desafiosea.clientes_backend.dto;

import lombok.Data;

@Data
public class EnderecoResponseDTO {
    private String cepMask;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
}