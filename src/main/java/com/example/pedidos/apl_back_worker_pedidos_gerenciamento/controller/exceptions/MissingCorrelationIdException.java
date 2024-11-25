package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions;

public class MissingCorrelationIdException extends ControllerException {
    public MissingCorrelationIdException() {
        super("CorrelationId é obrigatório");
    }
}
