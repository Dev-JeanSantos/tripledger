# Healthcheck API Call Example

## Endpoint
`GET /health`

## Curl Example
```
curl -X GET http://localhost:8080/health
```

## Response Example
```json
{
  "status": "UP"
}
```

## Description
Verifica se a aplicação está ativa. Não requer autenticação.

