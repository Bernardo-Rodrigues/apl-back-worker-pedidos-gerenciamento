package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProcessarPedidoUseCase {
    private final PedidoRepository pedidoRepository;

    public ProcessarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido execute(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
