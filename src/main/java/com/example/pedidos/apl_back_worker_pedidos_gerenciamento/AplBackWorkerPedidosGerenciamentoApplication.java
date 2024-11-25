package com.example.pedidos.apl_back_worker_pedidos_gerenciamento;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AplBackWorkerPedidosGerenciamentoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AplBackWorkerPedidosGerenciamentoApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.run(args);
	}
}