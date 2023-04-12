package com.example.demo.service

import com.example.demo.clients.CostCalculatorClient
import com.example.demo.services.CustomerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class CustomerServiceTests {
    @Autowired
    lateinit var customerService: CustomerService

    @MockBean
    lateinit var costCalculatorClient: CostCalculatorClient

    @Test
    fun addsSurcharge() {
        val customerCost = 100f

        `when`(costCalculatorClient.calculateCustomerCost(anyList())).thenReturn(customerCost)
        val costResponse = customerService.calculateCost()

        assertEquals(costResponse.cost, customerCost * 1.2f)
    }

}
