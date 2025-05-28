package com.desafiosea.clientes_backend.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class ClienteRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "[\\p{L}0-9 ]+", message = "Nome permite apenas letras, números e espaços")
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    private String cpf;

    @Valid
    @NotNull(message = "Endereço é obrigatório")
    private EnderecoDTO endereco;

    @Valid
    @NotNull(message = "Telefones são obrigatórios")
    @Size(min = 1, message = "É preciso cadastrar pelo menos um telefone")
    private List<TelefoneDTO> telefones;

    @Valid
    @NotNull(message = "E-mails são obrigatórios")
    @Size(min = 1, message = "É preciso cadastrar pelo menos um e-mail")
    private List<@Email(message = "E-mail inválido") String> emails;

    // getters e setters...
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public EnderecoDTO getEndereco() { return endereco; }
    public void setEndereco(EnderecoDTO endereco) { this.endereco = endereco; }

    public List<TelefoneDTO> getTelefones() { return telefones; }
    public void setTelefones(List<TelefoneDTO> telefones) { this.telefones = telefones; }

    public List<String> getEmails() { return emails; }
    public void setEmails(List<String> emails) { this.emails = emails; }
}