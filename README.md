# Simple Payment Platform

Este projeto simula uma API REST de transações simples para fins de prática de desenvolvimento backend com Java e Spring.

A aplicação permite:
- cadastro de usuários;
- listagem de usuários;
- criação de transações entre usuários com regras de validação;
- tratamento centralizado de exceções e respostas de erro;
- execução de testes automatizados.

O objetivo principal é estudar a construção de uma API REST em camadas (controller, service, repository), com foco em boas práticas de organização, validação de regras de negócio e testes.

## Tecnologias e ferramentas

- Java 21+
- Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- H2 Database (em memória)
- Lombok
- JUnit
- Mockito

## Pré-requisitos para execução

- JDK 21 ou superior instalado
- Maven 3.9+ instalado (ou uso do Maven Wrapper do projeto)
- Uma IDE de sua preferência para programar e executar.

## Como executar

### Com Maven Wrapper

No Windows:

```bash
./mvnw.cmd spring-boot:run
```

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

### Com Maven instalado

```bash
mvn spring-boot:run
```

## Como executar os testes

Com Maven Wrapper (Windows):

```bash
./mvnw.cmd test
```

Com Maven Wrapper (Linux/macOS):

```bash
./mvnw test
```

Ou com Maven instalado:

```bash
mvn test
```
---

## 🇺🇸 English Version

This project simulates a simple transaction REST API for backend practice using Java and Spring.

The application provides:
- user creation;
- user listing;
- transaction creation between users with validation rules;
- centralized exception handling and error responses;
- automated test execution.

The main goal is to study how to build a layered REST API (`controller`, `service`, `repository`), with focus on clean organization, business rule validation, and testing.

## Technologies and tools

- Java 21+
- Maven
- Spring Boot
- Spring MVC
- Spring Data JPA
- H2 Database (in-memory)
- Lombok
- JUnit
- Mockito

## Runtime requirements

- JDK 21 or higher installed
- Maven 3.9+ installed (or use the project's Maven Wrapper)
- An IDE of your preference to code and run

## Running the application

### With Maven Wrapper

On Windows:

```bash
./mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

### With Maven installed

```bash
mvn spring-boot:run
```

## Running tests

With Maven Wrapper (Windows):

```bash
./mvnw.cmd test
```

With Maven Wrapper (Linux/macOS):

```bash
./mvnw test
```

Or with Maven installed:

```bash
mvn test
```
