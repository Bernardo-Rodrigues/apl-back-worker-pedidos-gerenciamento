package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private Long id;

    private OrderStatus status;

    private BigDecimal totalValue;

    private List<Item> items;

    public Order(Long id, OrderStatus status, BigDecimal totalValue, List<Item> items) {
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

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> itens) {
        this.items = itens;
    }
}
