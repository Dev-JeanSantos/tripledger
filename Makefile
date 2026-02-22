# Makefile for Tripledger project

.PHONY: up down logs

up:
	cd infra && docker-compose up -d

down:
	cd infra && docker-compose down

logs:
	cd infra && docker-compose logs -f

