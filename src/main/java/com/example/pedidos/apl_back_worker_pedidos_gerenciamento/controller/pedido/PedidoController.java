package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.ProcessarPedidoUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private ProcessarPedidoUseCase processarPedidoUseCase;

    public void processarPedido(PedidoDTO pedidoDTO) {
        processarPedidoUseCase.execute(pedidoDTO);
    }
}
