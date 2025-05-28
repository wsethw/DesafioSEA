package com.desafiosea.clientes_backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class TelefoneDTO {

    @NotBlank(message = "Tipo do telefone é obrigatório")
    private String tipo;

    @NotBlank(message = "Número do telefone é obrigatório")
    @Pattern(
        regexp = "\\d{10,11}",
        message = "Número deve conter apenas dígitos, 10 ou 11 caracteres"
    )
    private String numero;

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
}