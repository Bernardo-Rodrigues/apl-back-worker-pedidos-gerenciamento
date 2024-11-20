package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findByProductId(Long productId);
    Optional<Order> findById(Long id);
    Order save(Order order);
}
