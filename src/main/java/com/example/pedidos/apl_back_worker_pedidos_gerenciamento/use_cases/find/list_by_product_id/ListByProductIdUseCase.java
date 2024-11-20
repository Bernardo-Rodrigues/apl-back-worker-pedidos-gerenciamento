package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.list_by_product_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers.FindOrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListByProductIdUseCase {

    private final OrderRepository orderRepository;

    public ListByProductIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> execute(Long productId) {
        return orderRepository.findByItemsProductId(productId)
                .stream()
                .map(FindOrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
