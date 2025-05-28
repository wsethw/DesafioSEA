package com.desafiosea.clientes_backend.controller;

import com.desafiosea.clientes_backend.dto.ClienteRequestDTO;
import com.desafiosea.clientes_backend.dto.ClienteResponseDTO;
import com.desafiosea.clientes_backend.mapper.ClienteMapper;
import com.desafiosea.clientes_backend.model.Cliente;
import com.desafiosea.clientes_backend.repository.ClienteRepository;
import com.desafiosea.clientes_backend.service.ViaCepService;

import javax.validation.Valid;       
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private ClienteMapper mapper;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(
            @Valid @RequestBody ClienteRequestDTO req) {

        Cliente cliente = mapper.toEntity(req);

        // Busca dados de endereço no ViaCEP
        cliente.setEndereco(viaCepService.consultarCep(cliente.getEndereco().getCep()));

        // Mantém complemento se foi informado pelo usuário
        String comp = req.getEndereco().getComplemento();
        if (comp != null) {
            cliente.getEndereco().setComplemento(comp);
        }

        Cliente salvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(mapper.toDto(salvo));
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        List<ClienteResponseDTO> dtos = clienteRepository.findAll().stream()
            .map(mapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
            .map(mapper::toDto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO req) {

        return clienteRepository.findById(id)
            .map(existing -> {
                Cliente atualizado = mapper.toEntity(req);

                // Se o CEP mudou, faz nova consulta; senão reaproveita o endereço existente
                if (!existing.getEndereco().getCep()
                       .equals(atualizado.getEndereco().getCep())) {
                    atualizado.setEndereco(
                      viaCepService.consultarCep(atualizado.getEndereco().getCep()));
                } else {
                    atualizado.setEndereco(existing.getEndereco());
                }

                atualizado.setId(id);
                Cliente salvo = clienteRepository.save(atualizado);
                return ResponseEntity.ok(mapper.toDto(salvo));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}