package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.mappers;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.ItemEntity;

public class ItemEntityMapper {

    public static ItemEntity toEntity(Item item) {
        if (item == null) return null;

        ItemEntity entity = new ItemEntity();
        entity.setId(item.getId());
        entity.setQuantity(item.getQuantity());
        entity.setProduct(ProductEntityMapper.toEntity(item.getProduct()));
        return entity;
    }

    public static Item toDomain(ItemEntity entity) {
        if (entity == null) return null;

        Item item = new Item();
        item.setId(entity.getId());
        item.setQuantity(entity.getQuantity());
        item.setProduct(ProductEntityMapper.toDomain(entity.getProduct()));
        return item;
    }
}
