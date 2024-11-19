package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.mappers;


import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.ItemDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.ProductDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.ProdutoDTO;
import org.hibernate.query.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Pedido pedido) {
        return new OrderDTO(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getItens().stream()
                        .map(item -> new ItemDTO(item.getId(), item.getQuantidade(), new ProductDTO(item.getProduto().getId(), item.getProduto().getPreco())))
                        .collect(Collectors.toList())
        );
    }
}
