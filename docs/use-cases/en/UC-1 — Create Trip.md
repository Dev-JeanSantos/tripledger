# UC-1 — Create Trip

## Module
Trip

## Description
Allows a user to create a new Trip, defining name, initial budget, dates, and participants.

## Primary Actor
User

## Input
CreateTripCommand:
- name: String (required, minimum 3 characters)
- initialBudget: BigDecimal (optional, >= 0)
- ownerUserId: String (required)
- startDate: LocalDate (required, >= today)
- endDate: LocalDate (required, > startDate)
- participants: List<String> (optional, must include owner)

## Business Rules

1. The trip name must be unique for the owner user.
2. The initial budget, if provided, must be greater than or equal to zero.
3. The ownerUserId must exist and be active.
4. The owner is automatically added as a participant.
5. Participants must be valid and active users.
6. The trip must be created with status = PLANNED.
7. startDate must be greater than or equal to the current date.
8. endDate must be greater than startDate.
9. tripId must be generated internally (UUID).
10. There must not be another active trip with the same name for the owner.

## Success Response

CreateTripResponse:
- tripId: String
- name: String
- status: String (PLANNED)
- participantCount: Int
- totalBudget: BigDecimal
- startDate: LocalDate
- endDate: LocalDate
- participants: List<String>

## Exceptions

- InvalidBudgetException
- UserNotFoundException
- InvalidParticipantException
- InvalidDatesException
- DuplicateNameException

## Notes
- The owner can add/remove participants while the trip is in PLANNED status.
- It is not allowed to create trips with retroactive dates.
- The budget can be adjusted later, respecting business rules.

