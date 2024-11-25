package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions;

public class DuplicatedCorrelationIdException extends ControllerException {
    public DuplicatedCorrelationIdException() {
        super("CorrelationId já processado");
    }
}
