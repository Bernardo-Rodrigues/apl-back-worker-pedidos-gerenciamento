package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException(Long orderId) {
        super("Product with ID " + orderId + " not found");
    }
}
