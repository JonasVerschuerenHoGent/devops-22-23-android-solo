package com.example.test.domain

data class Project(
    var id: Int,
    var name: String,
    var virtualMachines: List<VirtualMachine>? = null,
    var customerId: Int,
    var customer: Customer? = null,
    var state: String,
)
