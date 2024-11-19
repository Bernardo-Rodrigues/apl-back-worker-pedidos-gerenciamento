package com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories;

import com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Consulta pedidos com base no ID do Produto associado ao Item
    List<Pedido> findByItensProdutoId(Long produtoId);
}
