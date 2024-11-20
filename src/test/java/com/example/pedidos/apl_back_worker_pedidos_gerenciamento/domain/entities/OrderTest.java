package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();

        Item item1 = new Item(new Product(1l, BigDecimal.valueOf(10.00)), 2);
        Item item2 = new Item(new Product(2l, BigDecimal.valueOf(20.00)), 1);

        order.setItems(Arrays.asList(item1, item2));
    }

    @Test
    void calcularValorTotalTest() {
        order.calculateTotalValue();

        BigDecimal valorEsperado = BigDecimal.valueOf(40.00);
        assertEquals(valorEsperado, order.getTotalValue());
    }
}
