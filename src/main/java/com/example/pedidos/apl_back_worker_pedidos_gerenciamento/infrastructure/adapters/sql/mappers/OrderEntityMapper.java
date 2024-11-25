package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.OrderEntity;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.ItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class OrderEntityMapper {

    public static OrderEntity toEntity(Order order) {
        if (order == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setStatus(order.getStatus());
        entity.setTotalValue(order.getTotalValue());

        if (order.getItems() != null) {
            List<ItemEntity> itemEntities = order.getItems().stream()
                    .map(ItemEntityMapper::toEntity)
                    .peek(itemEntity -> itemEntity.setOrderEntity(entity))
                    .collect(Collectors.toList());
            entity.setItems(itemEntities);
        }

        return entity;
    }

    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        Order order = new Order();
        order.setId(entity.getId());
        order.setStatus(entity.getStatus());
        order.setTotalValue(entity.getTotalValue());

        if (entity.getItems() != null) {
            List<Item> items = entity.getItems().stream()
                    .map(ItemEntityMapper::toDomain)
                    .collect(Collectors.toList());
            order.setItems(items);
        }

        return order;
    }
}
