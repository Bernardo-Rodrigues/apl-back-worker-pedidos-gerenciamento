package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.order;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions.DuplicatedCorrelationIdException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions.MissingCorrelationIdException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.services.CorrelationIdService;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.CreateUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private CreateUseCase createUseCase;

    @Mock
    private CorrelationIdService correlationIdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_ShouldThrowMissingCorrelationIdException_WhenCorrelationIdIsNull() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of());

        assertThrows(MissingCorrelationIdException.class, () ->
                orderController.create(null, createOrderDTO)
        );

        verifyNoInteractions(correlationIdService, createUseCase);
    }

    @Test
    void create_ShouldThrowMissingCorrelationIdException_WhenCorrelationIdIsBlank() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of());

        assertThrows(MissingCorrelationIdException.class, () ->
                orderController.create(" ", createOrderDTO)
        );

        verifyNoInteractions(correlationIdService, createUseCase);
    }

    @Test
    void create_ShouldThrowDuplicatedCorrelationIdException_WhenCorrelationIdIsDuplicate() {
        String correlationId = "duplicate-id";
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of());

        when(correlationIdService.isDuplicate(correlationId)).thenReturn(true);

        assertThrows(DuplicatedCorrelationIdException.class, () ->
                orderController.create(correlationId, createOrderDTO)
        );

        verify(correlationIdService).isDuplicate(correlationId);
        verifyNoInteractions(createUseCase);
    }

    @Test
    void create_ShouldExecuteUseCaseAndRegisterCorrelationId_WhenCorrelationIdIsValid() {
        String correlationId = "valid-id";
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(List.of());

        when(correlationIdService.isDuplicate(correlationId)).thenReturn(false);

        orderController.create(correlationId, createOrderDTO);

        verify(correlationIdService).isDuplicate(correlationId);
        verify(createUseCase).execute(correlationId, createOrderDTO);
        verify(correlationIdService).registerCorrelationId(correlationId);
    }
}
