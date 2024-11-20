package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}
