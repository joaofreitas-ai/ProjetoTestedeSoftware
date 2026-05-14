# CineDNA

Sistema de recomendação de filmes desenvolvido em Java, com foco em personalização de recomendações e testes automatizados.

---

## Sobre o projeto

O CineDNA foi desenvolvido como projeto acadêmico da disciplina de Testes de Software.

O sistema recomenda filmes com base no perfil do usuário, considerando:

- gêneros preferidos
- duração dos filmes
- idioma
- classificação etária
- histórico e avaliações

Além disso, o sistema apresenta uma justificativa textual para cada recomendação.

Exemplo:

```text
Recomendamos Interestelar porque você gosta de
Ficção Científica + Drama.
```

---

## Tecnologias utilizadas

- Java
- JUnit 5
- Mockito
- IntelliJ IDEA

---

## Funcionalidades

- Cadastro de perfil cinéfilo
- Sistema de score de compatibilidade
- Filtragem de filmes
- Recomendações personalizadas
- Justificativa da recomendação
- Testes unitários
- Testes com Mockito

---

## Estrutura do projeto

```text
src/
 ├── model/
 ├── service/
 ├── util/
 ├── exception/
 └── test/
```

---

## Como executar o projeto

### 1. Clonar o repositório

```bash
git clone URL_DO_REPOSITORIO
```

### 2. Abrir no IntelliJ IDEA

Abra a pasta do projeto normalmente pelo IntelliJ.

### 3. Executar a aplicação

Execute a classe:

```text
Main.java
```

Localização:

```text
src/main/java/br/com/main/Main.java
```

---

## Como executar os testes

No IntelliJ:

```text
Clique com o botão direito na pasta test → Run Tests
```

Também é possível executar individualmente as classes de teste.

---

## Tipos de testes implementados

### JUnit

Utilizado para validar:
- regras de negócio
- cálculo de score
- filtragem de filmes
- limites e exceções

### Mockito

Utilizado para:
- simular dependências externas
- testar recomendações sem depender da API real
- validar chamadas de métodos
- criar cenários controlados

Exemplo:

```java
when(catalogo.buscarTodos())
    .thenReturn(List.of(f1, f2));
```

---

## Diagramas

O projeto contém:
- diagrama de classes
- diagrama de sequência

---

## Integrantes

- joão Eduardo Santos
- Mateus Almeida Bonfim
- Peter Moreira Marins

---

## Objetivo acadêmico

O objetivo do projeto foi aplicar conceitos de:
- orientação a objetos
- arquitetura em camadas
- testes automatizados
- mocks e isolamento de dependências
- modelagem UML
