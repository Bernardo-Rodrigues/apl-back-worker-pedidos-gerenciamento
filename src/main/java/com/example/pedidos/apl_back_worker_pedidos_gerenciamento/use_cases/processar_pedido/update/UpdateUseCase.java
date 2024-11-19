package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.update;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.update.dto.UpdateOrderDTO;
import org.springframework.stereotype.Service;

@Service
public class UpdateUseCase {
    private final PedidoRepository pedidoRepository;

    public UpdateUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido execute(UpdateOrderDTO orderDTO) {
        var orderStatus = orderDTO.status();
        if (!orderStatus.equals("CANCELLED") && !orderStatus.equals("FINISHED")) {
            throw new RuntimeException("Invalid status");
        }
        var order = pedidoRepository.findById(orderDTO.id())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        order.updateStatus(orderStatus);

        return pedidoRepository.save(order);
    }
}
