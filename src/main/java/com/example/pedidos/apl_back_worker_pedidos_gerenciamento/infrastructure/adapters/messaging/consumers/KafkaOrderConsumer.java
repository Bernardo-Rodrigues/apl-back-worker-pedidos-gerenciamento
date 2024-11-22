package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging.consumers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.order.OrderController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.dto.UpdateOrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static java.lang.String.format;

@Component
public class KafkaOrderConsumer {
    @Autowired
    private OrderController orderController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOG = Logger.getLogger(KafkaOrderConsumer.class.getName());

    @KafkaListener(topics = "${spring.kafka.orders.topic}", groupId = "${spring.kafka.orders.consumer.group-id}")
    public void consumeCreateOrder(
            @Header(name = "correlationId", required = false) String correlationId,
            @Payload CreateOrderDTO createOrderDTO,
            Acknowledgment acknowledgment) {
        try {
            LOG.info("Consumed CorrelationId: " + correlationId + " - message: " + createOrderDTO);
            orderController.create(correlationId ,createOrderDTO);
        } catch (Exception ex) {
            LOG.severe(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
        }finally {
            acknowledgment.acknowledge();
            LOG.info("Exiting listener");
        }
    }

    @KafkaListener(topics = "${spring.kafka.orders.update.topic}", groupId = "${spring.kafka.orders.consumer.group-id}")
    public void consumeUpdateOrder(
            @Payload UpdateOrderDTO updateOrderDTO,
            Acknowledgment acknowledgment) {
        try {
            LOG.info("Consumed message: " +  updateOrderDTO);
            orderController.update(updateOrderDTO);
        } catch (Exception ex) {
            LOG.severe(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
        }finally {
            acknowledgment.acknowledge();
            LOG.info("Exiting listener");
        }
    }
}
