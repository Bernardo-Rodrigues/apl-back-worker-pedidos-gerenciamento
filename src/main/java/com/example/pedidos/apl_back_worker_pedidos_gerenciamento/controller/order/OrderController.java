package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.order;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions.DuplicatedCorrelationIdException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.exceptions.MissingCorrelationIdException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.services.CorrelationIdService;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.dto.OrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.find_by_id.FindByIdUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.find.list_by_product_id.ListByProductIdUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.CreateUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.UpdateUseCase;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.dto.UpdateOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CreateUseCase createUseCase;

    @Autowired
    private UpdateUseCase updateUseCase;

    @Autowired
    private FindByIdUseCase findByIdUseCase;

    @Autowired
    private ListByProductIdUseCase listByProductIdUseCase;

    @Autowired
    private CorrelationIdService correlationIdService;

    public void create(String correlationId, CreateOrderDTO createOrderDTO) {
        if (correlationId == null || correlationId.isBlank()){
            throw new MissingCorrelationIdException();
        }

        if (correlationIdService.isDuplicate(correlationId)){
            throw new DuplicatedCorrelationIdException();
        };

        createUseCase.execute(correlationId, createOrderDTO);

        correlationIdService.registerCorrelationId(correlationId);
    }

    public void update(UpdateOrderDTO updateOrderDTO) {
        updateUseCase.execute(updateOrderDTO);
    }

    public OrderDTO getById(Long id) {
        return findByIdUseCase.execute(id);
    }

    public List<OrderDTO> listByProductId(Long productId) {
        return listByProductIdUseCase.execute(productId);
    }
}
