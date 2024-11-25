package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByItemEntitiesProductEntityId(Long productId);
}
