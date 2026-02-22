package com.fourtk.academy.tripledger.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    @Value("\${service.shared.secret}")
    private val serviceKey: String
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val authorizationFilter = AuthorizationFilter(this.serviceKey)
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(*AuthorizationFilter.IGNORE_AUTH_PATH_LIST).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(authorizationFilter, BasicAuthenticationFilter::class.java)
            .headers { it.disable() }
        http.sessionManagement { sessionManager ->
            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
        return http.build()
    }
}
