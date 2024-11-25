package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.converters.OrderStatusConverter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedido")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal totalValue;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "orderEntity")
    @JsonManagedReference
    private List<ItemEntity> itemEntities;

    public OrderEntity(Long id, OrderStatus status, BigDecimal totalValue, List<ItemEntity> itemEntities) {
        this.id = id;
        this.status = status;
        this.totalValue = totalValue;
        this.itemEntities = itemEntities;
    }

    public OrderEntity(){}

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

    public List<ItemEntity> getItems() {
        return this.itemEntities;
    }

    public void setItems(List<ItemEntity> itens) {
        this.itemEntities = itens;
    }
}
