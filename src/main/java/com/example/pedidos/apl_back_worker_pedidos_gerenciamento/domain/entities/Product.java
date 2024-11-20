package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import java.math.BigDecimal;

public class Product {

    private Long id;

    private BigDecimal price;

    public Product(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Product(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
