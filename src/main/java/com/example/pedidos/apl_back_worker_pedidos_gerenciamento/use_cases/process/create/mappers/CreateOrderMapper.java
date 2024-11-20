package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderMapper {

    public static Order toEntity(CreateOrderDTO orderDTO) {
        Order order = new Order();

        List<Item> itens = orderDTO.items().stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setQuantity(itemDTO.quantity());
                    item.setProduct(new Product(itemDTO.product().id()));
                    item.setOrder(order);

                    return item;
                })
                .collect(Collectors.toList());

        order.setItems(itens);

        order.setStatus(orderDTO.status());

        return order;
    }
}
