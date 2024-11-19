package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions;

public class InvalidStatusException extends BusinessException {
    public InvalidStatusException(String status) {
        super("Invalid order status: " + status);
    }
}
