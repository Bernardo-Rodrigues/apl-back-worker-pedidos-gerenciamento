package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories.impl;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Order;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories.OrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.mappers.OrderEntityMapper;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.entities.OrderEntity;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.sql.repositories.JpaOrderRepository;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.exceptions.DatabaseException;
import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.process.update.UpdateUseCase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Component
public class OrderRepositoryAdapter implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    private static final Logger LOG = Logger.getLogger(OrderRepositoryAdapter.class.getName());

    public OrderRepositoryAdapter(JpaOrderRepository jpaOrderRepository) {
        this.jpaOrderRepository = jpaOrderRepository;
    }

    @Override
    public List<Order> findByProductId(Long productId) {
        try{
           return jpaOrderRepository.findByItemEntitiesProductEntityId(productId)
                   .stream()
                   .map(OrderEntityMapper::toDomain)
                   .toList();
        } catch (Exception ex) {
            throw new DatabaseException(ex);
        }
    }

    @Override
    public Optional<Order> findById(Long id) {
        try{
            return jpaOrderRepository.findById(id)
                    .map(OrderEntityMapper::toDomain);
        } catch (Exception ex) {
            throw new DatabaseException(ex);
        }
    }

    @Override
    public Order save(Order order) {
        try{
            OrderEntity entity = OrderEntityMapper.toEntity(order);
            OrderEntity savedEntity = jpaOrderRepository.save(entity);
            return OrderEntityMapper.toDomain(savedEntity);
        } catch (Exception ex) {
            throw new DatabaseException(ex);
        }
    }

}
