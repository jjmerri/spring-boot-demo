package com.example.demo.database.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Table(name = "account")
@Entity
class AccountEntity(
    @Id
    @GeneratedValue
    var id: Int? = null,

    @Column
    var type: String? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    var customer: CustomerEntity? = null,
)
