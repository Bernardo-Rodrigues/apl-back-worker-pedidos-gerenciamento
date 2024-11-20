package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDTO(
        @JsonProperty("quantidade") int quantity,
        @JsonProperty("produto") ProductDTO product
) {}