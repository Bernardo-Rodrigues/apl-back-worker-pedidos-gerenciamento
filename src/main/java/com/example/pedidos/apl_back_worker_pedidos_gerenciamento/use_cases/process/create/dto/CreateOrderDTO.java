package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto;

import java.util.List;

public record CreateOrderDTO(List<ItemDTO> items, String status) {}
