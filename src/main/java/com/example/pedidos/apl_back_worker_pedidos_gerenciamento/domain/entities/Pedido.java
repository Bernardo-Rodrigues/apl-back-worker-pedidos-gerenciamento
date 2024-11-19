package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long produtoId;
    private BigDecimal valorTotal;
    private Status status;

    public enum Status {
        AGUARDANDO_PAGAMENTO, PAGO
    }
}
