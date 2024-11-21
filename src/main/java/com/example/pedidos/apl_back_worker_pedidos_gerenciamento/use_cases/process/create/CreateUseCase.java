package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Item;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Product;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.enums.OrderStatus;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.exceptions.ProductNotFoundException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.producers.OrderProducer;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.ProductRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.dto.CreateOrderDTO;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.create.mappers.CreateOrderMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateUseCase {
    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final ProductRepository productRepository;

    public CreateUseCase(OrderRepository orderRepository, OrderProducer orderProducer, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.productRepository = productRepository;
    }

    public void execute(String correlationId, CreateOrderDTO orderDTO) {
        Order order = CreateOrderMapper.toEntity(orderDTO);

        for (Item item : order.getItems()) {
            var productId = item.getProduct().getId();
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

            item.setProduct(product);
        }

        order.calculateTotalValue();
        order.setStatus(OrderStatus.PENDING);

        var savedOrder = orderRepository.save(order);

        orderProducer.sendCalculatedOrderMessage(correlationId, savedOrder.getId());
    }
}
