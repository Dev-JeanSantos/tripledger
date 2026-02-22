package com.fourtk.academy.tripledger.application

import org.springframework.stereotype.Service

interface HealthCheckUseCase {
    fun check(): HealthStatus
}

@Service
class HealthCheckUseCaseImpl : HealthCheckUseCase {
    override fun check(): HealthStatus = HealthStatus(status = "UP")
}

data class HealthStatus(val status: String)
