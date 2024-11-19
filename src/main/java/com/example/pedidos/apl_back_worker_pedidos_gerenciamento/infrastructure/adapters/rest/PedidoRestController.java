package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.rest;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PedidoRestController {

    private final PedidoRepository pedidoRepository;

    public PedidoRestController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/{id}")
    public Pedido getById(@PathVariable Long id) {
        return pedidoRepository.findById(id).orElse(null);
     }

    @GetMapping("/products/{productId}")
    public List<Pedido> getByProductId(@PathVariable Long productId) {
        return pedidoRepository.findByItensProdutoId(productId);
    }
}
