package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
        Long id,
        OrderStatus status,
        BigDecimal totalValue,
        List<ItemDTO> items
) {}
