package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

public class Item {

    private Long id;

    private int quantity;

    private Product product;

    private Order order;

    public Item(Product product, Integer quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public Item(){}

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
