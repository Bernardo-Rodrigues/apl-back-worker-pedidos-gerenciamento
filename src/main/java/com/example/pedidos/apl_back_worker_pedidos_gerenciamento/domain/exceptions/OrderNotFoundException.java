package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(Long orderId) {
        super("Order with ID " + orderId + " not found");
    }
}
