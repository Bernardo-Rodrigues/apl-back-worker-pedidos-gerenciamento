package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.rest;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido.PedidoController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class PedidoRestController {

    private final PedidoController pedidoController;

    public PedidoRestController(PedidoController pedidoController) {
        this.pedidoController = pedidoController;
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Long id) {
        return pedidoController.getById(id);
     }

    @GetMapping("/products/{productId}")
    public List<OrderDTO> getByProductId(@PathVariable Long productId) {
        return pedidoController.getByProductId(productId);
    }
}
