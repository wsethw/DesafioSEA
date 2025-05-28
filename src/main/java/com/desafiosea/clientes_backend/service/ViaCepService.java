package com.desafiosea.clientes_backend.service;

import com.desafiosea.clientes_backend.model.Endereco;
import com.desafiosea.clientes_backend.viacep.ViaCepResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL = "https://viacep.com.br/ws/{cep}/json";

    public Endereco consultarCep(String cepSemMascara) {
        ViaCepResponse resp = restTemplate.getForObject(URL, ViaCepResponse.class, cepSemMascara);
        if (resp == null || resp.getCep() == null) {
            throw new IllegalArgumentException("CEP não encontrado: " + cepSemMascara);
        }

        Endereco end = new Endereco();
        end.setCep(resp.getCep().replaceAll("\\D", ""));       // já sem máscara
        end.setLogradouro(resp.getLogradouro());
        end.setBairro(resp.getBairro());
        end.setCidade(resp.getCidade());
        end.setUf(resp.getUf());
        return end;
    }
}