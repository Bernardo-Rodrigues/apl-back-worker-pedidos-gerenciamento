package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto;


import java.math.BigDecimal;
import java.util.List;

public record OrderDTO(
        Long id,
        String status,
        BigDecimal totalValue,
        List<ItemDTO> items
) {}
