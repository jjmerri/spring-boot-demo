# spring-boot-demo

Minimal Spring Boot Example with Okta Auth and Liquibase DB migrations

## Customize Auth

Change the spring.security.oauth2.resourceserver.jwt.issuer-uri property in application.yml to your Okta auth server
issuer-uri and modify the HttpSecurityConfig class to have your custom scope (or leave permitAll to bypass security) on
the /customers endpoint.

## Build

`./gradlew build`

## Test

`./gradlew test`

## Run

`./gradlew bootRun`
