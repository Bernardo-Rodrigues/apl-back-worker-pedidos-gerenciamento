package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.*;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.ProductNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.ProductRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.ItemDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.ProductDTO;
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
class CreateUseCaseTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateUseCase createUseCase;

    @Test
    void testExecute_ShouldProcessPedidoSuccessfully() {
        Product product = new Product(1L, new BigDecimal("50.00"));
        Item item = new Item(product, 2);
        CreateOrderDTO orderDTO = new CreateOrderDTO(List.of(new ItemDTO(item.getQuantity(), new ProductDTO(item.getProduct().getId()))));

        Item item1 = new Item(product, 2);
        List<Item> items = Arrays.asList(item1);
        Order order = new Order(1L, OrderStatus.PENDING, new BigDecimal("0.00"), items);
        order.calculateTotalValue();

        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        Order result = createUseCase.execute(orderDTO);

        assertNotNull(result);
        assertEquals(new BigDecimal("100.00"), result.getTotalValue());
        Mockito.verify(productRepository).findById(1L);
        Mockito.verify(orderRepository).save(Mockito.any(Order.class));
    }

    @Test
    void testExecute_ShouldThrowException_WhenProdutoNotFound() {
        Product product = new Product(1L, new BigDecimal("50.00"));
        Item item = new Item(product, 2);
        CreateOrderDTO orderDTO = new CreateOrderDTO(List.of(new ItemDTO(item.getQuantity(), new ProductDTO(item.getProduct().getId()))));

        Mockito.when(productRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        RuntimeException exception = assertThrows(ProductNotFoundException.class, () -> createUseCase.execute(orderDTO));
        assertEquals(new ProductNotFoundException(product.getId()).getMessage(), exception.getMessage());
    }
}
