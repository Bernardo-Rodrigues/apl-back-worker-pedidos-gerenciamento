package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id.FindByIdUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_product_id.FindByProductIdUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.ProcessarPedidoUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private ProcessarPedidoUseCase processarPedidoUseCase;

    @Autowired
    private FindByIdUseCase findByIdUseCase;

    @Autowired
    private FindByProductIdUseCase findByProductIdUseCase;

    public void processarPedido(PedidoDTO pedidoDTO) {
        processarPedidoUseCase.execute(pedidoDTO);
    }

    public OrderDTO getById(@PathVariable Long id) {
        return findByIdUseCase.execute(id);
    }

    public List<OrderDTO> getByProductId(@PathVariable Long productId) {
        return findByProductIdUseCase.execute(productId);
    }
}
