package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto;

public record ItemDTO(
        Long id,
        int quantity,
        ProductDTO product
) {}
