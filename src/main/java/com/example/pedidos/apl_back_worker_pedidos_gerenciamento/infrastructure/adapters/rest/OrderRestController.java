package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.rest;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.order.OrderController;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderRestController {

    private final OrderController orderController;

    public OrderRestController(OrderController orderController) {
        this.orderController = orderController;
    }

    @GetMapping("/{id}")
    public OrderDTO getById(@PathVariable Long id) {
        return orderController.getById(id);
     }

    @GetMapping("/products/{productId}")
    public List<OrderDTO> listByProductId(@PathVariable Long productId) {
        return orderController.listByProductId(productId);
    }
}
