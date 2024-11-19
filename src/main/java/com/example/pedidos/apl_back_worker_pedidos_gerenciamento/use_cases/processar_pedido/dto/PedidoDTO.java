package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto;

import java.util.List;

public record PedidoDTO(List<ItemDTO> itens, String status) {}
