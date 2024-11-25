package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.ItemDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.ProductDTO;

import java.util.stream.Collectors;

public class FindOrderMapper {

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getStatus(),
                order.getTotalValue(),
                order.getItems().stream()
                        .map(item -> new ItemDTO(item.getId(), item.getQuantity(), new ProductDTO(item.getProduct().getId(), item.getProduct().getPrice())))
                        .collect(Collectors.toList())
        );
    }
}
