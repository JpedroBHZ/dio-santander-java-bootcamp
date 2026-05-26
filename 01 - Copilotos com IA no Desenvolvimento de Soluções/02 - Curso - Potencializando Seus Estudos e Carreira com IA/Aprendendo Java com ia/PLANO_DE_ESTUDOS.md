# 🚀 Plano de Estudos: Java & Backend Developer (Santander Bootcamp DIO 2026)

Bem-vindo ao seu plano de estudos prático! Este documento foi estruturado especificamente para guiar o seu aprendizado durante o **Bootcamp Santander 2026 - Imersão em Java** da DIO, focando em preparar você para os desafios práticos e as entrevistas de processos seletivos para vagas de desenvolvimento Backend.

---

## 📅 Cronograma de Estudos Sugerido (6 Semanas)

Abaixo está uma sugestão de divisão de carga horária para você equilibrar teoria, exercícios práticos do bootcamp e criação de portfólio.

| Semana | Foco Principal | Projetos Relacionados | Status |
| :---: | :--- | :--- | :---: |
| **1** | Fundamentos do Java & Controle de Fluxo | [ContaTerminal.java](file:///c:/Users/JPedr/OneDrive/Área%20de%20Trabalho/Aprendendo%20Java%20com%20ia/modulo-01-fundamentos/ContaTerminal.java) & [DesafioControleFluxo.java](file:///c:/Users/JPedr/OneDrive/Área%20de%20Trabalho/Aprendendo%20Java%20com%20ia/modulo-01-fundamentos/DesafioControleFluxo.java) | `[ ]` |
| **2** | Programação Orientada a Objetos (POO) | [BancoDigital.java](file:///c:/Users/JPedr/OneDrive/Área%20de%20Trabalho/Aprendendo%20Java%20com%20ia/modulo-02-poo/BancoDigital.java) (Desafio Prático) | `[ ]` |
| **3** | Java Collections & Streams API (Java Moderno) | [ExemploStreams.java](file:///c:/Users/JPedr/OneDrive/Área%20de%20Trabalho/Aprendendo%20Java%20com%20ia/modulo-03-streams/ExemploStreams.java) (Programação Funcional) | `[ ]` |
| **4** | Banco de Dados Relacionais & SQL | Modelagem de Banco de Dados e Queries básicas | `[ ]` |
| **5** | Spring Boot & API REST (JPA, Validation) | [SimuladorSpringAPI.java](file:///c:/Users/JPedr/OneDrive/Área%20de%20Trabalho/Aprendendo%20Java%20com%20ia/modulo-05-spring/SimuladorSpringAPI.java) (Arquitetura REST) | `[ ]` |
| **6** | Testes Unitários & Revisão para Vagas | JUnit 5, Mockito & MockMvc | `[ ]` |

---

## 📚 Detalhamento dos Módulos & Dicas de Ouro

### 1. Fundamentos do Java (Semana 1)
* **O que focar:** Sintaxe, tipos primitivos vs. Wrappers (ex: `int` vs `Integer`), operadores aritméticos e lógicos, estruturas de controle condicional (`if`, `switch-case`) e de repetição (`for`, `while`, `do-while`).
* **Tratamento de Exceções:** Entender a diferença entre *Checked Exceptions* (que herdam diretamente de `Exception` e exigem tratamento obrigatório) e *Unchecked Exceptions* (que herdam de `RuntimeException`).
* **Dica de Ouro:** Nunca ignore exceções com blocos `catch` vazios. No backend, a rastreabilidade através de logs e o tratamento correto de erros definem a estabilidade do sistema.

### 2. Programação Orientada a Objetos - POO (Semana 2)
* **O que focar:** Os 4 pilares:
  1. **Abstração:** Representar entidades do mundo real em modelos de código.
  2. **Encapsulamento:** Proteger o estado de um objeto com escopos (`private`, `protected`, `public`) e métodos acessores (Getters/Setters).
  3. **Herança:** Compartilhar atributos e métodos comuns entre classes mãe e filhas (`extends`).
  4. **Polimorfismo:** Capacidade de um objeto ser referenciado de várias formas (sobrescrita `@Override` e sobrecarga de métodos).
* **Classes Abstratas vs Interfaces:** Entenda que interfaces definem *comportamentos/contratos* que uma classe assume, enquanto classes abstratas representam uma *identidade comum* parcial.

### 3. Collections & Streams API (Semana 3)
* **O que focar:** 
  * As principais estruturas da API de Coleções: `List` (ArrayList), `Set` (HashSet, não aceita duplicatas) e `Map` (HashMap, chave-valor).
  * Programação Funcional no Java com a **Streams API** utilizando Expressões Lambda e Method References.
* **Dica de Ouro:** A Streams API permite manipular dados com poucas linhas e máxima eficiência. Funções como `filter()`, `map()`, `sorted()` e `collect()` são largamente utilizadas no processamento de dados vindos do banco de dados no backend.

### 4. Banco de Dados & SQL (Semana 4)
* **O que focar:** Bancos de dados relacionais (PostgreSQL, MySQL). Entender tabelas, chaves primárias (PK), chaves estrangeiras (FK), relacionamentos (1:1, 1:N, N:N) e comandos SQL fundamentais (`SELECT`, `INSERT`, `UPDATE`, `DELETE`, `JOINs`).
* **Dica de Ouro:** Pratique escrever consultas SQL puras antes de partir para a automação do Spring Data JPA. Isso ajudará você a diagnosticar problemas de performance no futuro (como o famoso problema do select N+1).

### 5. Spring Boot & APIs RESTful (Semana 5)
* **O que focar:** 
  * Injeção de Dependência (DI) e Inversão de Controle (IoC) comandados pelo Spring Container.
  * Desenvolvimento de endpoints RESTful usando `@RestController`, `@GetMapping`, `@PostMapping`, etc.
  * Mapeamento de Entidades com JPA / Hibernate e comunicação com banco usando `Spring Data JPA`.
  * Validação de campos de entrada utilizando `@Valid` e o Bean Validation.
* **Boas Práticas de APIs:** Utilize DTOs (Data Transfer Objects) para não expor suas entidades de banco de dados diretamente na web, e implemente um `@ControllerAdvice` para tratar exceções globalmente e retornar erros formatados em JSON.

### 6. Testes Unitários com JUnit 5 (Semana 6)
* **O que focar:** 
  * Escrita de testes rápidos isolados com JUnit 5.
  * Uso de assertions comuns (`assertEquals`, `assertTrue`, `assertThrows`).
  * Utilização do **Mockito** para mockar/simular comportamentos de dependências (ex: simular o banco de dados para testar a regra de negócio do Service).
* **Dica de Ouro:** Empresas de tecnologia valorizam imensamente desenvolvedores que escrevem testes de software automatizados. Ter cobertura de testes nos seus projetos de portfólio no GitHub é um grande diferencial competitivo.

---

## 🛠️ Como Executar os Códigos Modelos Deste Workspace

Você não precisa de IDEs pesadas no início para estudar os fundamentos básicos e POO. Se você tiver o Java Development Kit (JDK) versão 11 ou superior instalado no seu sistema, você pode executar os códigos deste workspace diretamente pelo terminal:

1. Abra o Terminal do Windows (PowerShell ou CMD).
2. Navegue até a pasta do módulo desejado:
   ```powershell
   cd "modulo-01-fundamentos"
   ```
3. Execute o código Java diretamente:
   ```powershell
   java ContaTerminal.java
   ```

---

## 🏁 Checklist de Conclusão Prática

Marque seu progresso conforme você estuda e pratica cada modelo:

- [ ] Entendi a sintaxe básica e leitura de dados com Scanner em `ContaTerminal.java`
- [ ] Compreendi a lógica de exceções personalizadas em `DesafioControleFluxo.java`
- [ ] Fixei os pilares de POO (Herança/Polimorfismo) com `BancoDigital.java`
- [ ] Pratiquei manipulação de listas e expressões lambda com `ExemploStreams.java`
- [ ] Estudei a arquitetura de camadas do Spring com `SimuladorSpringAPI.java`

*Dica: Você pode editar este arquivo Markdown e marcar com `[x]` as caixinhas para acompanhar seu progresso! Bons estudos!*
