package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories.impl;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.ProductRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.ProductEntity;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.mappers.ProductEntityMapper;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories.JpaProductRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.exceptions.DatabaseException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    public Optional<Product> findById(Long id) {
        try{
            return jpaProductRepository.findById(id)
                    .map(ProductEntityMapper::toDomain);
        } catch (Exception ex) {
            throw new DatabaseException(ex);
        }
    }
}
