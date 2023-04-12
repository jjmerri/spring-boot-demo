package com.example.demo.controllers

import com.example.demo.clients.CostCalculatorClient
import com.example.demo.database.entities.CustomerEntity
import com.example.demo.services.CostResponse
import com.example.demo.services.CustomerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
    val customerService: CustomerService,
) {
    @GetMapping("/customers")
    fun getCustomers(): ResponseEntity<List<CustomerEntity>> {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomers())
    }

    @GetMapping("/customers/{id}")
    fun getCustomers(
        @PathVariable id: Int,
    ): ResponseEntity<CustomerEntity> {
        customerService.getCustomer(id)?.let {
            return ResponseEntity.status(HttpStatus.OK).body(it)
        } ?: run {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @GetMapping("/customers/cost")
    fun getCost(): ResponseEntity<CostResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.calculateCost())
    }
}

@Configuration
class CustomerControllerConfig {
    @Bean
    fun costCalculator(): CostCalculatorClient {
        return CostCalculatorClient()
    }
}
