package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
class HttpSecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .requestMatchers("/actuator/**").permitAll()
//                .requestMatchers("/customers/**").hasAuthority("SCOPE_example:read")
                .requestMatchers("/customers/**").permitAll()
                .anyRequest().denyAll()
                .and().oauth2ResourceServer().jwt()
        }
            .headers { headers -> headers.frameOptions().disable() }
            .csrf { csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")) }
        return http.build()
    }
}
