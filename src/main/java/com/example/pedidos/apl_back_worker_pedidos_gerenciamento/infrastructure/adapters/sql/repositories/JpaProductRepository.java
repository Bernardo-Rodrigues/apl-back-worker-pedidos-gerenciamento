package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long>{
}
