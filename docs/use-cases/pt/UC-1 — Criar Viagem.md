# UC-1 — Criar Viagem

## Módulo
Viagem

## Descrição
Permite que um usuário crie uma nova Viagem, definindo nome, orçamento inicial, datas e participantes.

## Ator Principal
Usuário

## Entrada
CreateTripCommand:
- name: String (obrigatório, mínimo 3 caracteres)
- initialBudget: BigDecimal (opcional, >= 0)
- ownerUserId: String (obrigatório)
- startDate: LocalDate (obrigatório, >= hoje)
- endDate: LocalDate (obrigatório, > startDate)
- participants: List<String> (opcional, deve incluir o dono)

## Regras de Negócio

1. O nome da viagem deve ser único para o usuário dono.
2. O orçamento inicial, se informado, deve ser maior ou igual a zero.
3. O ownerUserId deve existir e estar ativo.
4. O dono é automaticamente adicionado como participante.
5. Participantes devem ser usuários válidos e ativos.
6. A viagem deve ser criada com status = PLANNED.
7. startDate deve ser maior ou igual à data atual.
8. endDate deve ser maior que startDate.
9. tripId deve ser gerado internamente (UUID).
10. Não pode haver outra viagem ativa com o mesmo nome para o dono.

## Resposta de Sucesso

CreateTripResponse:
- tripId: String
- name: String
- status: String (PLANNED)
- participantCount: Int
- totalBudget: BigDecimal
- startDate: LocalDate
- endDate: LocalDate
- participants: List<String>

## Exceções

- InvalidBudgetException
- UserNotFoundException
- InvalidParticipantException
- InvalidDatesException
- DuplicateNameException

## Observações
- O dono pode adicionar/remover participantes enquanto a viagem estiver em status PLANNED.
- Não é permitido criar viagens com datas retroativas.
- O orçamento pode ser ajustado posteriormente, respeitando regras de negócio.
