package com.fourtk.academy.tripledger.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.web.filter.OncePerRequestFilter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

class AuthorizationFilter(
    private val serviceKey: String
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (verifyIgnoreAuthorization(request)) {
            filterChain.doFilter(request, response)
            return
        }

        request.getHeader(HttpHeaders.AUTHORIZATION)?.let {
            if (it == this.serviceKey) {
                SecurityContextHolder.getContext().authentication = this.systemAuthorization()
            }
        }

        if (SecurityContextHolder.getContext().authentication == null) {
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.use {
                it.write(buildObject(request, response))
                it.flush()
            }
            return
        }
        filterChain.doFilter(request, response)
    }

    private fun buildObject(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ) = """{"timestamp":"${OffsetDateTime.now(ZoneOffset.UTC)}","status":${res.status},"error":"${HttpStatus.valueOf(res.status)}","path":"${req.requestURI}"}"""

    private fun verifyIgnoreAuthorization(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        return IGNORE_AUTH_PATH_LIST.any { path == it || path.startsWith(it.removeSuffix("/**")) }
    }

    private fun systemAuthorization(): PreAuthenticatedAuthenticationToken {
        return PreAuthenticatedAuthenticationToken(
            this.serviceKey,
            null, Collections.singletonList(GrantedAuthority { ROLE_SYSTEM })
        )
    }

    companion object {
        const val ROLE_SYSTEM = "ROLE_SYSTEM"
        @JvmField
        val IGNORE_AUTH_PATH_LIST =
            arrayOf(
                "/health",
                "/health/",
                "/metrics",
                "/metrics/",
                "/actuator",
                "/actuator/",
                "/error",
                "/swagger-ui.html",
                "/swagger-ui/",
                "/v3/api-docs/",
                "/v3/api-docs.yaml",
                "/webjars/",
                "/swagger-resources",
                "/swagger-resources/",
                "/docs",
                "/info",
                "/cache",
                "/favicon.ico",
                "/swagger.yaml",
                "/csrf"
            )
    }
}
