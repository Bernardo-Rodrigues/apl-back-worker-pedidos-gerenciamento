# apl-back-worker-pedidos-gerenciamento
Projeto para gerenciamento de pedidos via Kafka

# spring-boot-clean-architecture

Esqueleto de Microsserviço baseado no clean code e na clean architecture com Spring Boot

## Domain (Domínio)
Aqui é onde mora o domínio da nossa aplicação e suas regras de negócio.

* Se a entidade possuir um identificado único, utilize a anotation `@Entity` ao nível de classe e `@Id` ao nível do atributo identificador

Módulo: `domain`, caminho: `/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/domain`

## Use Cases (Casos de uso)
Aqui é onde moram as automatizações que criamos da necessidade de negócio que temos.

* Para usar bancos de dados, mensageria, ..., devemos usar interfaces que serão posteriormente implementadas na camada de infraestrutura.

Módulo: `use_cases`, caminho: `/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/use_cases`

## Data Transfer Objects (DTOs)
Não podemos cruzar fronteiras com as nossas entidades. Portanto, aqui moram os objetos que transferem dados entre a camada de use_cases e a controller.

Módulo: `use_cases`, caminho: `/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/use_cases/(nome)/dto`

## Controllers (Adaptadores de interface)
Aqui é onde moram as conversões que fazemos entre as camadas que acessam a nossa aplicação e os use cases.
* Geralmente, as camadas externas da aplicação não entregam exatamente o que um `use_case` espera receber para o processamento.
* Aqui podem ser utilizadas interfaces de conversão

Módulo: `controller`, caminho: `/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/controller`

## Infrastructure (External interfaces)
Aqui é onde moram as as tecnologias que queremos acoplar à nossa aplicação.
É nessa camada onde implementamos as interfaces definidas em `controllers`, `use_cases`, e `domain` da forma que quisermos.

Se desejarmos usar Hibernate, JDBC, utilizar algum framework para expor nossas APIs,... temos a liberdade de realizar essa implementação utilizando qualquer biblioteca que desejarmos.

Também é onde implementamos os testes de integração, onde podemos subir containers utilizando TestContainers para realizarmos testes mais precisos do comportamento da nossa aplicação dentro das tecnologias escolhidas.

Módulo: `infrastructure`, caminhos:
`/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/infrastructure/adapters`
`/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/infrastructure/configuration`
`/src/main/java/com/example/pedidos/apl_back_worker_pedidos_gerenciamento/infrastructure/{conforme a necessidade}`

