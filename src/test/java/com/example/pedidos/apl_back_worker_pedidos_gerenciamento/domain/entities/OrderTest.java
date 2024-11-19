package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();

        Item item1 = new Item();
        item1.setProduto(new Produto(1l, BigDecimal.valueOf(10.00)));
        item1.setQuantidade(2);

        Item item2 = new Item();
        item2.setProduto(new Produto(2l, BigDecimal.valueOf(20.00)));
        item2.setQuantidade(1);

        pedido.setItens(Arrays.asList(item1, item2));
    }

    @Test
    void calcularValorTotalTest() {
        pedido.calcularValorTotal();

        BigDecimal valorEsperado = BigDecimal.valueOf(40.00);
        assertEquals(valorEsperado, pedido.getValorTotal());
    }
}
