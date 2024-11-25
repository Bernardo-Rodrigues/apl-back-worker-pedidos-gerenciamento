package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        BigDecimal price
) {}
