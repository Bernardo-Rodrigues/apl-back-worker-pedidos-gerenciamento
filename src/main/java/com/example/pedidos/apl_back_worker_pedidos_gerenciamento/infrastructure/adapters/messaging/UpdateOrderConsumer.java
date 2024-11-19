package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido.PedidoController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.update.dto.UpdateOrderDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static java.lang.String.format;

@Component
public class UpdateOrderConsumer {
    @Autowired
    private PedidoController pedidoController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOG = Logger.getLogger(UpdateOrderConsumer.class.getName());

    @KafkaListener(topics = "${spring.kafka.orders.update.topic}", groupId = "${spring.kafka.orders.consumer.group-id}")
    public void consume(String message, Acknowledgment acknowledgment) {
        try {
            LOG.info(format("Consumed message: %s", message));

            UpdateOrderDTO orderDTO = objectMapper.readValue(message, UpdateOrderDTO.class);

            pedidoController.updateOrder(orderDTO);

            acknowledgment.acknowledge();
            LOG.info("Exiting listener");

        } catch (Exception ex) {
            LOG.severe(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
            acknowledgment.acknowledge();
            throw new RuntimeException("Error processing message", ex);
        }
    }
}
