package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateOrderDTO(@JsonProperty("itens") List<ItemDTO> items) {}