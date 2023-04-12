package com.example.demo.services

import com.example.demo.clients.CostCalculatorClient
import com.example.demo.database.entities.CustomerEntity
import com.example.demo.database.repositories.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val costCalculator: CostCalculatorClient,
) {
    fun getCustomers(): List<CustomerEntity> {
        return customerRepository.findAll()
    }

    fun getCustomer(id: Int): CustomerEntity? {
        return customerRepository.findByIdOrNull(id)
    }

    fun calculateCost(): CostResponse {
        val customerCost = costCalculator.calculateCustomerCost(getCustomers())

        // add the 20% surcharge
        return CostResponse(customerCost * 1.2f)
    }
}

data class CostResponse(var cost: Float)
