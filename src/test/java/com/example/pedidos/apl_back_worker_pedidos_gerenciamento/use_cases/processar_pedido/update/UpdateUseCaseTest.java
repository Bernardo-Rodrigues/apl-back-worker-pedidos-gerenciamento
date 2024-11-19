package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.update;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.InvalidStatusException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.OrderNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.PedidoRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido.update.dto.UpdateOrderDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateUseCaseTest {

    private final PedidoRepository pedidoRepository = mock(PedidoRepository.class);
    private final UpdateUseCase updateUseCase = new UpdateUseCase(pedidoRepository);

    @Test
    void shouldUpdateOrderSuccessfully() {
        // Arrange
        var pedido = new Pedido();
        pedido.setId(1L);
        pedido.setStatus("PENDING");

        var orderDTO = new UpdateOrderDTO(1L, "FINISHED");

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(Mockito.any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        var updatedOrder = updateUseCase.execute(orderDTO);

        // Assert
        assertNotNull(updatedOrder);
        assertEquals("FINISHED", updatedOrder.getStatus());
        verify(pedidoRepository, times(1)).findById(1L);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(2L, "CANCELLED");

        when(pedidoRepository.findById(2L)).thenReturn(Optional.empty());

        // Act & Assert
        var exception = assertThrows(OrderNotFoundException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new OrderNotFoundException(2L).getMessage(), exception.getMessage());
        verify(pedidoRepository, times(1)).findById(2L);
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }

    @Test
    void shouldThrowExceptionForInvalidStatus() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(1L, "INVALID");

        // Act & Assert
        var exception = assertThrows(InvalidStatusException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new InvalidStatusException("INVALID").getMessage(), exception.getMessage());
        verify(pedidoRepository, never()).findById(anyLong());
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }

    @Test
    void shouldThrowExceptionForEmptyStatus() {
        // Arrange
        var orderDTO = new UpdateOrderDTO(1L, "");

        // Act & Assert
        var exception = assertThrows(InvalidStatusException.class, () -> updateUseCase.execute(orderDTO));
        assertEquals(new InvalidStatusException("").getMessage(), exception.getMessage());
        verify(pedidoRepository, never()).findById(anyLong());
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }
}
