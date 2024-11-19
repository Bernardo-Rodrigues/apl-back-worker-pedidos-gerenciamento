package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Produto;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoMapper {

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();

        List<Item> itens = pedidoDTO.itens().stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setQuantidade(itemDTO.quantidade());
                    item.setProduto(new Produto(itemDTO.produto().id()));
                    item.setPedido(pedido);

                    return item;
                })
                .collect(Collectors.toList());

        pedido.setItens(itens);

        pedido.setStatus(pedidoDTO.status());

        return pedido;
    }
}
