package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.*;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.ProdutoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.ItemDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.PedidoDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.dto.ProdutoDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.mappers.PedidoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProcessarPedidoUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProcessarPedidoUseCase processarPedidoUseCase;

    @Test
    void testExecute_ShouldProcessPedidoSuccessfully() {
        Produto produto = new Produto(1L, new BigDecimal("50.00"));
        Item item = new Item(produto, 2);
        PedidoDTO pedidoDTO = new PedidoDTO(List.of(new ItemDTO(item.getQuantidade(), new ProdutoDTO(item.getProduto().getId()))), "PENDENTE");

        Item item1 = new Item(produto, 2);
        List<Item> itens = Arrays.asList(item1);
        Pedido pedido = new Pedido(1L, "PENDENTE", new BigDecimal("0.00"), itens);
        pedido.calcularValorTotal();

        Mockito.when(produtoRepository.findById(1L)).thenReturn(java.util.Optional.of(produto));
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedido);

        Pedido result = processarPedidoUseCase.execute(pedidoDTO);

        assertNotNull(result);
        assertEquals(new BigDecimal("100.00"), result.getValorTotal());
        Mockito.verify(produtoRepository).findById(1L);
        Mockito.verify(pedidoRepository).save(Mockito.any(Pedido.class));
    }

    @Test
    void testExecute_ShouldThrowException_WhenProdutoNotFound() {
        Produto produto = new Produto(1L, new BigDecimal("50.00"));
        Item item = new Item(produto, 2);
        PedidoDTO pedidoDTO = new PedidoDTO(List.of(new ItemDTO(item.getQuantidade(), new ProdutoDTO(item.getProduto().getId()))), "PENDENTE");

        Mockito.when(produtoRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> processarPedidoUseCase.execute(pedidoDTO));
        assertEquals("Produto não encontrado", exception.getMessage());
    }
}
