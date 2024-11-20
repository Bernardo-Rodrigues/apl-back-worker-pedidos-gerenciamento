package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.order.OrderController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static java.lang.String.format;

@Component
public class CreateOrderConsumer {
    @Autowired
    private OrderController orderController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger LOG = Logger.getLogger(CreateOrderConsumer.class.getName());

    @KafkaListener(topics = "${spring.kafka.orders.topic}", groupId = "${spring.kafka.orders.consumer.group-id}")
    public void consume(String message, Acknowledgment acknowledgment) {
        try {
            LOG.info(format("Consumed message: %s", message));

            CreateOrderDTO orderDTO = objectMapper.readValue(message, CreateOrderDTO.class);

            orderController.create(orderDTO);

            acknowledgment.acknowledge();
            LOG.info("Exiting listener");

        } catch (JsonProcessingException ex) {
            LOG.severe("Error Parsing Json");
        }catch (Exception ex) {
            LOG.severe(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
            throw new RuntimeException("Error processing message", ex);
        }finally {
            acknowledgment.acknowledge();
            LOG.info("Exiting listener");
        }
    }
}
