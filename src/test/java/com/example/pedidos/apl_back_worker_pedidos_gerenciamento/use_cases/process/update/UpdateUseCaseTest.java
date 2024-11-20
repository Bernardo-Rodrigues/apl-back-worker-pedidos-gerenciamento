package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.InvalidStatusException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.dto.UpdateOrderDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUseCaseTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final UpdateUseCase updateUseCase = new UpdateUseCase(orderRepository);

    @Test
    void shouldUpdateOrderSuccessfully() {
        // Arrange
        var pedido = new Order();
        pedido.setId(1L);
        pedido.setStatus("PENDING");

        var orderDTO = new UpdateOrderDTO(1L, "FINISHED");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(orderRepository.save(Mockito.any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var updatedOrder = updateUseCase.execute(orderDTO);

        // Assert
        assertNotNull(updatedOrder);
        assertEquals("FINISHED", updatedOrder.getStatus());
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(pedido);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(2L, "CANCELLED");

        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(OrderNotFoundException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new OrderNotFoundException(2L).getMessage(), exception.getMessage());
        verify(orderRepository, times(1)).findById(2L);
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionForInvalidStatus() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(1L, "INVALID");

        // Act & Assert
        var exception = assertThrows(InvalidStatusException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new InvalidStatusException("INVALID").getMessage(), exception.getMessage());
        verify(orderRepository, never()).findById(anyLong());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionForEmptyStatus() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(1L, "");

        // Act & Assert
        var exception = assertThrows(InvalidStatusException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new InvalidStatusException("").getMessage(), exception.getMessage());
        verify(orderRepository, never()).findById(anyLong());
        verify(orderRepository, never()).save(any(Order.class));
    }
}
