package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido.PedidoController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.ProcessarPedidoUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static java.lang.String.format;

@Component
public class PedidoConsumer {
    @Autowired
    private PedidoController pedidoController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOG = Logger.getLogger(PedidoConsumer.class.getName());

    @KafkaListener(topics = "${spring.kafka.orders.topic}", groupId = "${spring.kafka.orders.consumer.group-id}")
    public void consume(String message, Acknowledgment acknowledgment) {
        try {
            LOG.info(format("Consumed message: %s", message));

            PedidoDTO pedidoDTO = objectMapper.readValue(message, PedidoDTO.class);

            pedidoController.processarPedido(pedidoDTO);

            acknowledgment.acknowledge();
            LOG.info("Exiting listener");

        } catch (Exception ex) {
            LOG.severe(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
            acknowledgment.acknowledge();
            throw new RuntimeException("Error processing message", ex);
        }
    }
}
