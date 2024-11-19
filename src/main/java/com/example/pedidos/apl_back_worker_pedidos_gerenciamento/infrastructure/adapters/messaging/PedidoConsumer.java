package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.ProcessarPedidoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static java.lang.String.format;

@Component
public class PedidoConsumer {
    @Autowired
    private ProcessarPedidoUseCase processarPedidoUseCase;

    private static final Logger LOG = Logger.getLogger(PedidoConsumer.class.getName());

    @KafkaListener(topics = "pedidos", groupId = "group")
    public void consume(String message, Acknowledgment acknowledgment) {
        try {
            LOG.info(format("Consumed message: %s", message));

            Pedido pedido = new Pedido();
            pedido.setProdutoId(1L);
            pedido.setStatus(Pedido.Status.PAGO);
            pedido.setValorTotal(BigDecimal.valueOf(100));

            processarPedidoUseCase.execute(pedido);
            acknowledgment.acknowledge();
            LOG.info("Exiting listener");
        }catch (RuntimeException ex) {
            LOG.info(format("Error on Consumer Message and Commit [%s]", ex.getMessage()));
            throw ex;
        }
    }
}
