package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preco", nullable = false)
    private BigDecimal price;

    public ProductEntity(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public ProductEntity(Long id) {
        this.id = id;
    }

    public ProductEntity() {}

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
