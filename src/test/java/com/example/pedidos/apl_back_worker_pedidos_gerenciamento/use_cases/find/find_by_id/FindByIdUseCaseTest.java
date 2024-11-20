package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
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
    private OrderRepository orderRepository;

    @InjectMocks
    private FindByIdUseCase findByIdUseCase;

    @Test
    void testExecute_ShouldReturnOrderDTO_WhenPedidoExists() {
        Long orderId = 1L;
        Item item1 = new Item(new Product(1L, new BigDecimal("100.00")), 2);
        Item item2 = new Item(new Product(2L, new BigDecimal("50.00")), 2);

        List<Item> itens = Arrays.asList(item1, item2);

        Order order = new Order(orderId, "PENDENTE", new BigDecimal("0.00"), itens);

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        OrderDTO result = findByIdUseCase.execute(orderId);

        assertNotNull(result);
        Mockito.verify(orderRepository).findById(orderId);
    }

    @Test
    void testExecute_ShouldThrowException_WhenPedidoNotFound() {
        Long orderId = 1L;
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(OrderNotFoundException.class, () -> findByIdUseCase.execute(orderId));
        assertEquals(new OrderNotFoundException(orderId).getMessage(), exception.getMessage());
    }
}
