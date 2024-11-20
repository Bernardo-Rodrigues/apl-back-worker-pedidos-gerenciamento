package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByItemsProductId(Long productId);
}
