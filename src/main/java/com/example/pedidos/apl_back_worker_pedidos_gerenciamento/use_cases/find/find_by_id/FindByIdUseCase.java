package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers.FindOrderMapper;
import org.springframework.stereotype.Service;

@Service
public class FindByIdUseCase {

    private final OrderRepository orderRepository;

    public FindByIdUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO execute(Long id) {
        return orderRepository.findById(id)
                .map(FindOrderMapper::toDTO)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}
