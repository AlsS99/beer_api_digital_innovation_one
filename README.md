# 🍺 Beerstock - API REST de Gerenciamento de Estoque de Cervejas

[![Status do Projeto](https://img.shields.io/badge/Status-Concluído-brightgreen)](https://github.com/AlsS99/beer_api_digital_innovation_one.git)
[![Tecnologias](https://img.shields.io/badge/Stack-Java%20|%20Spring%20Boot%20|%20Maven-blue)](https://www.java.com/)

---

## 🎯 Sobre o Projeto

Este projeto consiste em uma **API REST** para o gerenciamento de estoques de cerveja. A aplicação permite realizar operações de **CRUD** (Criar, Listar, Consultar por Nome e Excluir) e, principalmente, controlar o **fluxo de estoque** de cada cerveja.

Este repositório foi desenvolvido como parte de um desafio da **Digital Innovation One (Expert Class)**, com foco em demonstrar proficiência em testes e desenvolvimento orientado a testes (TDD).

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 14+
* **Framework:** Spring Boot 2.x
* **Persistência:** Spring Data JPA
* **Banco de Dados:** H2 (Em Memória, para testes)
* **Construção:** Maven

---

## 🧪 Testes e Metodologias

O foco principal deste projeto foi a garantia da qualidade e a aplicação de boas práticas de desenvolvimento.

### 1. Test-Driven Development (TDD)

As funcionalidades de controle de estoque foram desenvolvidas utilizando a prática do **TDD**, onde os testes foram escritos *antes* do código de produção.

* **Funcionalidades TDD:**
    * **Incremento de Estoque:** Valida se é possível adicionar cervejas ao estoque, respeitando o limite máximo (`max`).
    * **Decremento de Estoque:** Valida se é possível remover cervejas do estoque, prevenindo que a quantidade seja negativa (estoque < 0).

### 2. Cobertura de Testes

O projeto possui uma **cobertura robusta de testes** em todas as camadas, garantindo o funcionamento do sistema de ponta a ponta.

* **Testes Unitários (JUnit e Mockito):** Focados em isolar e validar a lógica de negócios nas camadas **`Service`** e **`Controller`**, utilizando mocks para simular dependências.
* **Testes de Integração (`@DataJpaTest` e `@SpringBootTest`):** Focados em validar a comunicação entre as camadas:
    * `BeerRepositoryTest`: Testa a camada de persistência (JPA e H2).
    * `BeerIntegrationTest`: Testa o fluxo completo da API (Controller -> Service -> Repository).

---

## 🚀 Como Executar

Para rodar a aplicação e os testes, siga os passos abaixo:

### Pré-requisitos
* Java 14 ou superior
* Maven 3.6.3 ou superior
* Git

### Passos
1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)
    cd SEU_REPOSITORIO
    ```

2.  **Executar a Suíte de Testes (Recomendado):**
    ```bash
    mvn clean test
    ```

3.  **Executar a Aplicação:**
    ```bash
    mvn spring-boot:run
    ```

A API estará disponível em: `http://localhost:8080/api/v1/beers`

---

## 🔗 Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/beers` | Cria um novo registro de cerveja. |
| `GET` | `/api/v1/beers/{name}` | Busca uma cerveja pelo nome. |
| `GET` | `/api/v1/beers` | Lista todas as cervejas registradas. |
| `DELETE`| `/api/v1/beers/{id}` | Exclui uma cerveja pelo ID. |
| `PATCH`| `/api/v1/beers/{id}/increment` | Incrementa o estoque. |
| `PATCH`| `/api/v1/beers/{id}/decrement` | Decrementa o estoque. |

Abaixo, seguem links bem bacanas, sobre tópicos mencionados durante a aula:

* [SDKMan! para gerenciamento e instalação do Java e Maven](https://sdkman.io/)
* [Referência do Intellij IDEA Community, para download](https://www.jetbrains.com/idea/download)
* [Palheta de atalhos de comandos do Intellij](https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf)
* [Site oficial do Spring](https://spring.io/)
* [Site oficial JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
* [Site oficial Mockito](https://site.mockito.org/)
* [Site oficial Hamcrest](http://hamcrest.org/JavaHamcrest/)
* [Referências - testes em geral com o Spring Boot](https://www.baeldung.com/spring-boot-testing)
* [Referência para o padrão arquitetural REST](https://restfulapi.net/)
* [Referência pirâmide de testes - Martin Fowler](https://martinfowler.com/articles/practical-test-pyramid.html#TheImportanceOftestAutomation)

[Neste link](https://drive.google.com/file/d/1KPh19mvyKirorOI-UsEYHKkmZpet3Ks6/view?usp=sharing), seguem os slides apresentados como o roteiro utilizado para o desenvolvimento do projeto da nossa sessão.



