package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_product_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Produto;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindByProductIdUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private FindByProductIdUseCase findByProductIdUseCase;

    @Test
    void testExecute_ShouldReturnListOfOrderDTO_WhenPedidosExistForProduct() {
        Long productId = 1L;
        Item item1 = new Item(new Produto(1L, new BigDecimal("100.00")), 2);
        Item item2 = new Item(new Produto(2L, new BigDecimal("50.00")), 2);

        List<Item> itens = Arrays.asList(item1, item2);

        Pedido pedido = new Pedido(1L, "PENDENTE", new BigDecimal("0.00"), itens);

        Mockito.when(pedidoRepository.findByItensProdutoId(productId))
                .thenReturn(Stream.of(pedido).collect(Collectors.toList()));

        List<OrderDTO> result = findByProductIdUseCase.execute(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        Mockito.verify(pedidoRepository).findByItensProdutoId(productId);
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenNoPedidosFoundForProduct() {
        Long productId = 1L;
        Mockito.when(pedidoRepository.findByItensProdutoId(productId)).thenReturn(List.of());

        List<OrderDTO> result = findByProductIdUseCase.execute(productId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(pedidoRepository).findByItensProdutoId(productId);
    }
}
