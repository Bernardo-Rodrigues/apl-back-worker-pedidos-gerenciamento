package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers.OrderMapper;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.mappers.PedidoMapper;
import org.springframework.stereotype.Service;

@Service
public class FindByIdUseCase {

    private final PedidoRepository pedidoRepository;

    public FindByIdUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public OrderDTO execute(Long id) {
        return pedidoRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}
