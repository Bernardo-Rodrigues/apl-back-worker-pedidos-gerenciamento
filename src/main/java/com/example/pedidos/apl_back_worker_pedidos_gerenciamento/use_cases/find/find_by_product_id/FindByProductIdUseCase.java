package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_product_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindByProductIdUseCase {

    private final PedidoRepository pedidoRepository;

    public FindByProductIdUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<OrderDTO> execute(Long productId) {
        return pedidoRepository.findByItensProdutoId(productId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
