package com.example.demo.database.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Table(name = "customer")
@Entity
class CustomerEntity(
    @Id
    @GeneratedValue
    var id: Int? = null,

    @Column
    var name: String? = null,

    @OneToMany(mappedBy = "customer")
    var accounts: Set<AccountEntity> = setOf(),
)
