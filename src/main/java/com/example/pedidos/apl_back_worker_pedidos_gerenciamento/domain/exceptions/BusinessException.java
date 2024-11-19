package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions;

public abstract class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
