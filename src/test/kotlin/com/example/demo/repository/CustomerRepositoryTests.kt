package com.example.demo.repository

import com.example.demo.database.entities.AccountEntity
import com.example.demo.database.entities.CustomerEntity
import com.example.demo.database.repositories.CustomerRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
class CustomerRepositoryTests {
    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    @Transactional
    fun crud() {
        val customer = CustomerEntity(name = "Joe's Pool Hall")
        val accounts = mutableListOf(
            AccountEntity(type = "BUSINESS", customer = customer),
            AccountEntity(type = "PERSONAL", customer = customer),
        )
        customer.accounts = accounts

        val savedCustomer = customerRepository.saveAndFlush(customer)
        assertNotNull(savedCustomer.id)

        val retrievedCustomer = customerRepository.findByIdOrNull(savedCustomer.id)
        assertNotNull(retrievedCustomer)
        assertEquals(retrievedCustomer!!.accounts.size, 2)
        retrievedCustomer.accounts.forEach { account ->
            assertNotNull(account.id)
        }

        retrievedCustomer.name = "Joe's Pool Hall and Bar"
        retrievedCustomer.accounts.removeIf { account -> account.type == "PERSONAL" }
        val updatedCustomer = customerRepository.saveAndFlush(retrievedCustomer)
        assertEquals(updatedCustomer.name, "Joe's Pool Hall and Bar")
        assertEquals(updatedCustomer.accounts.size, 1)
        updatedCustomer.accounts.forEach { account ->
            assertEquals(account.type, "BUSINESS")
        }

        customerRepository.delete(updatedCustomer)
        val deletedCustomer = customerRepository.findByIdOrNull(updatedCustomer.id)
        assertNull(deletedCustomer)
    }

}
