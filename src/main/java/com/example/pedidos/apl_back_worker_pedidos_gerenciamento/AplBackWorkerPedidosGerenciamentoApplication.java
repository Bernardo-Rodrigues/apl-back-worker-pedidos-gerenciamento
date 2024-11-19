package com.example.pedidos.apl_back_worker_pedidos_gerenciamento;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({
		"com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.configuration",
		"com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.rest;",
		"com.example.pedidos.apl_back_worker_pedidos_gerenciamento.infrastructure.adapters.messaging",
		"com.example.pedidos.apl_back_worker_pedidos_gerenciamento.use_cases.processar_pedido",
		"com.example.pedidos.apl_back_worker_pedidos_gerenciamento.controller.pedido"
})
@EntityScan("com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.entities")
@EnableJpaRepositories("com.example.pedidos.apl_back_worker_pedidos_gerenciamento.domain.repositories")
public class AplBackWorkerPedidosGerenciamentoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AplBackWorkerPedidosGerenciamentoApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}
}