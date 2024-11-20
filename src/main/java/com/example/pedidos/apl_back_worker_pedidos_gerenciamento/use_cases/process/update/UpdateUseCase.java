package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.InvalidStatusException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging.UpdateOrderConsumer;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.dto.UpdateOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class UpdateUseCase {
    private final OrderRepository orderRepository;


    public UpdateUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order execute(UpdateOrderDTO orderDTO) {
        var orderStatus = OrderStatus.fromDescription(orderDTO.status());

        if (!OrderStatus.isCancelledOrFinished(orderStatus.name())) {
            throw new InvalidStatusException(orderStatus.name());
        }

        var order = orderRepository.findById(orderDTO.id())
                .orElseThrow(() -> new OrderNotFoundException(orderDTO.id()));

        order.updateStatus(orderStatus);

        return orderRepository.save(order);
    }

}
