package com.desafiosea.clientes_backend.repository;

import com.desafiosea.clientes_backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
