package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.ProductEntity;

public class ProductEntityMapper {

    public static ProductEntity toEntity(Product product) {
        if (product == null) return null;

        ProductEntity entity = new ProductEntity(product.getId(), product.getPrice());
        return entity;
    }

    public static Product toDomain(ProductEntity entity) {
        if (entity == null) return null;

        Product product = new Product(entity.getId(), entity.getPrice());
        return product;
    }
}
