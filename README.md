# events-demo

## Visão geral
POC Spring Boot que demonstra um fluxo de publicação em fanout (RabbitMQ) e persistência em MongoDB, seguindo princípios de Clean Architecture (use cases, dataproviders, entrypoints).

## Arquitetura
- Camada `core` (domínio e usecases): contém interfaces puras Java (sem dependência de frameworks).
- Camada `dataprovider`: implementações concretas (MongoDB, RabbitMQ).
- Camada `entrypoint`: controladores HTTP (REST).
- `configuration`: classes Spring para criar beans que conectam implementações com os usecases.

Estrutura principal:
- `src/main/java/dev/fbiopereira/eventsdemo/core`
- `src/main/java/dev/fbiopereira/eventsdemo/dataprovider`
- `src/main/java/dev/fbiopereira/eventsdemo/entrypoint`
- `src/main/java/dev/fbiopereira/eventsdemo/configuration`

## O que o sistema faz
- Cria entidades relacionadas a "fanout exchange" e filas.
- Persiste dados no MongoDB.
- Publica mensagens em um exchange fanout no RabbitMQ.
- Fornece endpoints REST para criar recursos e enviar mensagens.

## Tecnologias (ver `pom.xml`)
- Java 21 
- Spring Boot 3
- Spring Data MongoDB
- Spring AMQP (RabbitMQ)
- Maven
- Docker / docker-compose (dependencias)

## Como rodar (local)
1. Ajustar `src/main/resources/application.properties` com URLs/credentials do MongoDB e RabbitMQ.
2. Rodar serviços dependentes (opcional via Docker):
    - `docker-compose.yml` inclui serviços de MongoDB e RabbitMQ.
    - Com Docker Desktop ativo:
        - `docker compose up -d`
3. Rodar a aplicação:
    - Via Maven: `./mvnw spring-boot:run`
    - Ou empacotar: `./mvnw clean package` e `java -jar target/events-demo-*.jar`
4. A aplicação por padrão roda em `http://localhost:8080` (confirmar em `application.properties`).

## Endpoints (onde procurar os mapeamentos reais)

Após executar a aplicação a documentação OpenAPI/Swagger estará disponível em:

LOCAL: http://localhost:8484/swagger-ui/
DOCKER COMPOSE: http://localhost:8585/swagger-ui/

Verifique os controladores em:
- `src/main/java/dev/fbiopereira/eventsdemo/entrypoint/ExchangeFanoutController.java`
- `src/main/java/dev/fbiopereira/eventsdemo/entrypoint/ExchangeFanoutMessageController.java`

Exemplo genérico de endpoints (ajuste conforme controllers do projeto):
- `POST /api/v1/fanout/exchanges` — criar exchange (retorna 201)
- `POST /api/v1/fanout/exchanges/{exchange}/messages` — publicar mensagem no exchange
- `POST /api/v1/fanout/queues` — criar fila e bind com exchange

Exemplo `curl` (substituir path/payload conforme sua API):
```bash
curl -X POST http://localhost:8080/api/v1/fanout/exchanges \
  -H "Content-Type: application/json" \
  -d '{"name":"meu-exchange"}'
```

## Pendências

- Consumidor de mensagens
- Implementar testes unitários, integração e BDD.
- Implementar tratamento de erros e validações.
- Implementar monitoramento/logging.
