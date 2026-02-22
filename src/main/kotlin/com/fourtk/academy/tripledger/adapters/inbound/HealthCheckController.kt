package com.fourtk.academy.tripledger.adapters.inbound

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import com.fourtk.academy.tripledger.application.HealthCheckUseCase
import com.fourtk.academy.tripledger.application.HealthStatus
import org.springframework.beans.factory.annotation.Autowired

@RestController
class HealthCheckController @Autowired constructor(
    private val healthCheckUseCase: HealthCheckUseCase
) {
    @GetMapping("/health")
    fun health(): HealthStatus = healthCheckUseCase.check()
}
