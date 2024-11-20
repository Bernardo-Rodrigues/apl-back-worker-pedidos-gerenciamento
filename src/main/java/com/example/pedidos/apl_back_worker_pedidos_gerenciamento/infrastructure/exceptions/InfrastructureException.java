package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.exceptions;

public abstract class InfrastructureException extends RuntimeException {
    public InfrastructureException(String message) {
        super(message);
    }
}
