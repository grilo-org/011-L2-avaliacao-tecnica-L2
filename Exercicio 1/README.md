# 📦 API de Empacotamento de Pedidos

## 📌 Visão Geral da Aplicação

A aplicação desenvolvida é uma API REST que automatiza o processo de empacotamento de pedidos em caixas de papelão, otimizando o espaço utilizado. Dado um conjunto de pedidos contendo produtos com dimensões específicas, o sistema calcula a melhor forma de distribuí-los nas caixas disponíveis, minimizando o número de caixas utilizadas e garantindo um aproveitamento eficiente do espaço.

---

## 🛠 Tecnologias Utilizadas

### Backend

- **Java 17** → Linguagem principal, aproveitando recursos modernos como `records` e Stream API.
- **Spring Boot 3.3** → Framework para desenvolvimento rápido de APIs RESTful.
- **Spring Data JPA** → Facilita a interação com o banco de dados.
- **Spring Web** → Tratamento de requisições HTTP e construção de endpoints.
- **Spring Validation** → Validação de dados de entrada.
- **SpringDoc OpenAPI** → Documentação automática da API via Swagger UI.

### Banco de Dados

- **MySQL 8.0** → Armazena informações sobre caixas e pedidos.
- **Flyway** → Ferramenta de migração de banco de dados, garantindo versionamento e histórico de alterações.

### Infraestrutura

- **Docker** → Containerização da aplicação e do MySQL para fácil implantação.
- **Docker Compose** → Orquestração dos serviços (app + banco de dados).

### Testes

- **JUnit 5** → Framework para testes unitários e de integração.
- **Mockito** → Simulação de comportamentos em testes.

---

## 🎯 Estratégia de Solução

### 1. Modelagem do Problema

O desafio principal foi implementar um algoritmo que:

- Recebe uma lista de pedidos (cada um com produtos de dimensões variadas).
- Calcula a melhor combinação de caixas para armazenar os produtos, seguindo duas regras:
  - Cada produto deve caber em pelo menos uma caixa.
  - Minimizar o número de caixas utilizadas.

### 2. Algoritmo de Empacotamento

Foi implementada uma versão simplificada do problema do **"Bin Packing"**, com as seguintes estratégias:

- Ordenação das caixas por volume (menor → maior) para priorizar caixas menores.
- Alocação gulosa (**Greedy Algorithm**):
  - Para cada produto, verifica-se se ele cabe na caixa atual.
  - Se couber, é adicionado; caso contrário, abre-se uma nova caixa.

### 3. Fluxo da Aplicação

#### 📥 Entrada

Recebe um JSON com pedidos e produtos:

```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "produtos": [
        {"produto_id": "PS5", "dimensoes": {"altura": 40, "largura": 10, "comprimento": 25}}
      ]
    }
  ]
}
```

#### ⚙️ Processamento

O serviço `EmpacotamentoService` calcula a melhor distribuição.

#### 📤 Saída

Retorna um JSON indicando quais produtos foram alocados em cada caixa:

```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "caixas": [
        {
          "codigoCaixa": "CAIXA_G",
          "produtos": ["PS5"]
        }
      ]
    }
  ]
}
```

---

## ⚙ Técnicas e Boas Práticas Aplicadas

### ✅ Clean Code & Design Patterns

- **DTOs (Data Transfer Objects)** → Separação clara entre modelos de entrada/saída e entidades JPA.
- **Injeção de Dependência** → Uso de `@Autowired` e construtores para melhor testabilidade.
- **Single Responsibility Principle** → Cada classe tem uma única responsabilidade.

### ✅ Tratamento de Erros

- **Validação de Dados** → `@Valid` em endpoints para garantir dados corretos.
- **Exceções Customizadas** → Tratamento de casos como "produto não cabe em nenhuma caixa".

### ✅ Banco de Dados

- **Flyway** → Migrações SQL versionadas (`V1__Create_caixa_table.sql`).
- **JPA/Hibernate** → Mapeamento de entidades

### ✅ Documentação

- **Swagger/OpenAPI** → Documentação automática disponível em:  
  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### ✅ Testes

- **Testes Unitários** → Cobertura de serviços e controllers.
- **Testes de Integração** → Verificação de endpoints e banco de dados.

---

## 🚀 Conclusão

Esta aplicação resolve um problema real de logística (empacotamento de pedidos) usando tecnologias modernas e boas práticas de desenvolvimento.

### Próximos passos possíveis:

- Adicionar autenticação **JWT** para segurança.

O código está pronto para ser executado localmente (com Docker) ou implantado em cloud.

---

## 🔗 Acesso Rápido

- **API Docs**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

- **Docker**:
  ```bash
  docker-compose up --build
  ```

---

## 👨‍💻 Desenvolvido por Vinícius

📧 **Contato**: vinicius.bacelar11@outlook.com