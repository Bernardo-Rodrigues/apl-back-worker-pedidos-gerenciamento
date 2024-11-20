package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal totalValue;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    @JsonManagedReference
    private List<Item> items;

    public Order(Long id, String status, BigDecimal totalValue, List<Item> items) {
        this.id = id;
        this.status = status;
        this.totalValue = totalValue;
        this.items = items;
    }

    public Order(){}

    public void calculateTotalValue() {
        this.totalValue = items.stream()
                .map(item -> item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> itens) {
        this.items = itens;
    }
}
