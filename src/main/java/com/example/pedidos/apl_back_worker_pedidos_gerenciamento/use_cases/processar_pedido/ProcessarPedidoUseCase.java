package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Produto;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.ProdutoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.mappers.PedidoMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProcessarPedidoUseCase {
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ProcessarPedidoUseCase(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public Pedido execute(PedidoDTO pedidoDTO) {
        Pedido pedido = PedidoMapper.toEntity(pedidoDTO);

        for (Item item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            item.setProduto(produto);
        }

        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }
}
