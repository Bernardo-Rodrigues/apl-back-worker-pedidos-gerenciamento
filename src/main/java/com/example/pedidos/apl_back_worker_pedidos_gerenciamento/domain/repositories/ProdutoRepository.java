package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findById(Long id);
}
