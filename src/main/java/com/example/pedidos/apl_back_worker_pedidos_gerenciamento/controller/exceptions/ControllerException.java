package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions;

public abstract class ControllerException extends RuntimeException {
    public ControllerException(String message) {
        super(message);
    }
}
