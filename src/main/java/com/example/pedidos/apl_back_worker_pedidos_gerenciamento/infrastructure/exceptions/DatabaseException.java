package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.exceptions;

public class DatabaseException extends InfrastructureException {
    public DatabaseException(Exception ex) {
        super(ex.getMessage());
    }
}
