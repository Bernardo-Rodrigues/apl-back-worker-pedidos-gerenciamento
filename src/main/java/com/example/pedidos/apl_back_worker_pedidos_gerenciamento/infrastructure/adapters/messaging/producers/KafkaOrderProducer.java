package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging.producers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.producers.OrderProducer;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging.consumers.KafkaOrderConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaOrderProducer implements OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.orders.calculated.topic}")
    private String calculatedOrderTopic;

    private static final Logger LOG = Logger.getLogger(KafkaOrderProducer.class.getName());

    public KafkaOrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendCalculatedOrderMessage(String correlationId, Long orderId) {
        LOG.info("Message produced for correlationId: " + correlationId + " - orderId: " + orderId);

        ProducerRecord<String, String> record = new ProducerRecord<>(
                calculatedOrderTopic,
                null,
                orderId.toString()
        );

        record.headers().add("correlationId", correlationId.getBytes());

        kafkaTemplate.send(record);
    }
}
