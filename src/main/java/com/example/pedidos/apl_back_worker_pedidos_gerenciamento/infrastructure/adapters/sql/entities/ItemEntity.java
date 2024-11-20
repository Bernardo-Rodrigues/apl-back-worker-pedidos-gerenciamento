package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private OrderEntity orderEntity;

    public ItemEntity(ProductEntity productEntity, Integer quantity){
        this.productEntity = productEntity;
        this.quantity = quantity;
    }

    public ItemEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public ProductEntity getProduct() {
        return productEntity;
    }

    public void setProduct(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

}
