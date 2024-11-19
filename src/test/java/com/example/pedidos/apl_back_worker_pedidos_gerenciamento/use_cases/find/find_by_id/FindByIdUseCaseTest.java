package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Produto;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
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
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FindByIdUseCaseTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private FindByIdUseCase findByIdUseCase;

    @Test
    void testExecute_ShouldReturnOrderDTO_WhenPedidoExists() {
        Long pedidoId = 1L;
        Item item1 = new Item(new Produto(1L, new BigDecimal("100.00")), 2);
        Item item2 = new Item(new Produto(2L, new BigDecimal("50.00")), 2);

        List<Item> itens = Arrays.asList(item1, item2);

        Pedido pedido = new Pedido(pedidoId, "PENDENTE", new BigDecimal("0.00"), itens);

        Mockito.when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        OrderDTO result = findByIdUseCase.execute(pedidoId);

        assertNotNull(result);
        Mockito.verify(pedidoRepository).findById(pedidoId);
    }

    @Test
    void testExecute_ShouldThrowException_WhenPedidoNotFound() {
        Long pedidoId = 1L;
        Mockito.when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(OrderNotFoundException.class, () -> findByIdUseCase.execute(pedidoId));
        assertEquals(new OrderNotFoundException(pedidoId).getMessage(), exception.getMessage());
    }
}
