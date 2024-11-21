package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.producers;

public interface OrderProducer {
    void sendCalculatedOrderMessage(String correlationId, Long orderId);
}
