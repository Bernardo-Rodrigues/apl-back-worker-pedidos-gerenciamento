package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.list_by_product_id;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ListByProductIdUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ListByProductIdUseCase listByProductIdUseCase;

    @Test
    void testExecute_ShouldReturnListOfOrderDTO_WhenPedidosExistForProduct() {
        Long productId = 1L;
        Item item1 = new Item(new Product(1L, new BigDecimal("100.00")), 2);
        Item item2 = new Item(new Product(2L, new BigDecimal("50.00")), 2);

        List<Item> items = Arrays.asList(item1, item2);

        Order order = new Order(1L, "PENDENTE", new BigDecimal("0.00"), items);

        Mockito.when(orderRepository.findByItemsProductId(productId))
                .thenReturn(Stream.of(order).collect(Collectors.toList()));

        List<OrderDTO> result = listByProductIdUseCase.execute(productId);

        assertNotNull(result);
        assertEquals(1, result.size());
        Mockito.verify(orderRepository).findByItemsProductId(productId);
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenNoPedidosFoundForProduct() {
        Long productId = 1L;
        Mockito.when(orderRepository.findByItemsProductId(productId)).thenReturn(List.of());

        List<OrderDTO> result = listByProductIdUseCase.execute(productId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(orderRepository).findByItemsProductId(productId);
    }
}
