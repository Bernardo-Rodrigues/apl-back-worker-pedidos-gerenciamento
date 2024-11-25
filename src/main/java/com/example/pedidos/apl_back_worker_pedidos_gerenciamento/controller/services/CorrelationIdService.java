package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.services;

public interface CorrelationIdService {
    boolean isDuplicate(String correlationId);
    void registerCorrelationId(String correlationId);
}
