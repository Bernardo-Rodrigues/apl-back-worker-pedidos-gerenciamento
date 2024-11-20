package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.InvalidStatusException;

import java.util.Arrays;

public enum OrderStatus {
    PENDING("Pendente"),
    CANCELLED("Cancelado"),
    FINISHED("Finalizado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus fromDescription(String description) {
        return Arrays.stream(values())
                .filter(status -> status.description.equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() -> new InvalidStatusException(description));
    }

    public static boolean isCancelledOrFinished(String status) {
        return CANCELLED.name().equalsIgnoreCase(status) || FINISHED.name().equalsIgnoreCase(status);
    }
}
