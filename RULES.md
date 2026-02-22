# Regras Padrão – Clean Code + Arquitetura Hexagonal

## 1. Estrutura de Pastas (Hexagonal)

* `domain/` → entidades, serviços, interfaces do negócio (sem dependências externas).
* `application/` → casos de uso, coordenação de fluxo entre Domain e Adapters.
* `adapters/`
  * `inbound/` → REST, GraphQL, CLI, gRPC.
  * `outbound/` → repositórios, APIs externas, mensagens.
* `config/` → configuração de infraestrutura, DI, dependências.

## 2. Domain

* Contém apenas regras de negócio.
* Não deve depender de frameworks ou bibliotecas externas.
* Métodos pequenos, responsabilidade única.

## 3. Application

* Coordena fluxos entre Domain e Adapters.
* Chama apenas interfaces do Domain.

## 4. Adapters

* **Inbound:** expõe o sistema (APIs, CLI, gRPC).
* **Outbound:** implementa integrações externas.
* Logger permitido somente em Adapters.

## 5. Nomenclatura

* Classes, métodos e variáveis com nomes claros e descritivos.
* Evitar abreviações.
* Constantes em UPPER_SNAKE_CASE.

## 6. Clean Code e SOLID

* SRP, OCP, LSP, ISP, DIP.
* Métodos curtos (máx. 20 linhas).
* Evitar comentários desnecessários; prefira nomes claros.

## 7. Tratamento de Erros e Validação

* Exceções customizadas no Domain.
* Validação próxima da entrada (Controller ou Use Case).

## 8. Testes

* Domain → regras de negócio.
* Application → fluxos.
* Adapters → integração.

## 9. Estilo de Código

* Indentação 4 espaços, linhas até 120 caracteres.
* Null safety, preferir imutabilidade (`val` ou `final`).
* Seguir padrão do projeto (ex: Ktlint para Kotlin, Google Java Style para Java).

## 10. Prompt Default para Copilot

> Sempre gerar código seguindo Clean Code, SOLID e Arquitetura Hexagonal.
> Domain sem dependências externas, Application coordena fluxo, Adapters expõem e integram.
> Código testável, nomes claros, métodos pequenos e responsabilidade única.

