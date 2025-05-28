package com.desafiosea.clientes_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String cpfMask;
    private EnderecoResponseDTO endereco;
    private List<TelefoneResponseDTO> telefones;
    private List<String> emails;
}