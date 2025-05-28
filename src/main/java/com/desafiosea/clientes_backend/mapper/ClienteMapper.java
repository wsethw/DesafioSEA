package com.desafiosea.clientes_backend.mapper;

import com.desafiosea.clientes_backend.dto.*;
import com.desafiosea.clientes_backend.model.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    private String maskCpf(String cpf) {
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})",
                                 "$1.$2.$3-$4");
    }

    private String maskCep(String cep) {
        return cep.replaceFirst("(\\d{5})(\\d{3})", "$1-$2");
    }

    private String maskTelefone(String numero, String tipo) {
        if ("celular".equals(tipo)) {
            return numero.replaceFirst("(\\d{2})(\\d{1})(\\d{4})(\\d{4})",
                                       "($1) $2$3-$4");
        } else {
            return numero.replaceFirst("(\\d{2})(\\d{4})(\\d{4})",
                                       "($1) $2-$3");
        }
    }

    public Cliente toEntity(ClienteRequestDTO dto) {
        Cliente c = new Cliente();
        c.setNome(dto.getNome());
        c.setCpf(dto.getCpf());

        Endereco e = new Endereco();
        e.setCep(dto.getEndereco().getCep());
        e.setComplemento(dto.getEndereco().getComplemento());
        c.setEndereco(e);

        c.setEmails(dto.getEmails());

        List<Telefone> telefones = dto.getTelefones().stream().map(tdto -> {
            Telefone t = new Telefone();
            t.setTipo(tdto.getTipo());
            t.setNumero(tdto.getNumero());
            return t;
        }).collect(Collectors.toList());
        c.setTelefones(telefones);

        return c;
    }

    public ClienteResponseDTO toDto(Cliente c) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setCpfMask(maskCpf(c.getCpf()));

        EnderecoResponseDTO er = new EnderecoResponseDTO();
        er.setCepMask(maskCep(c.getEndereco().getCep()));
        er.setLogradouro(c.getEndereco().getLogradouro());
        er.setBairro(c.getEndereco().getBairro());
        er.setCidade(c.getEndereco().getCidade());
        er.setUf(c.getEndereco().getUf());
        er.setComplemento(c.getEndereco().getComplemento());
        dto.setEndereco(er);

        List<TelefoneResponseDTO> tdtos = c.getTelefones().stream().map(t -> {
            TelefoneResponseDTO tr = new TelefoneResponseDTO();
            tr.setTipo(t.getTipo());
            tr.setNumeroMask(maskTelefone(t.getNumero(), t.getTipo()));
            return tr;
        }).collect(Collectors.toList());
        dto.setTelefones(tdtos);

        dto.setEmails(c.getEmails());
        return dto;
    }
}