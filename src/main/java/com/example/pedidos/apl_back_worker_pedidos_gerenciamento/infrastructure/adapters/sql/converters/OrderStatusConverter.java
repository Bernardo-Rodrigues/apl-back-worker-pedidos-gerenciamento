package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.converters;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        if (status == null) {
            return null;
        }
        return status.getDescription();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String descricao) {
        if (descricao == null) {
            return null;
        }
        return OrderStatus.fromDescription(descricao);
    }
}