package com.example.demo.clients

import com.example.demo.database.entities.CustomerEntity

class CostCalculatorClient {
    fun calculateCustomerCost(customers: List<CustomerEntity>): Float {
        return customers.size * 10.5f
    }
}
