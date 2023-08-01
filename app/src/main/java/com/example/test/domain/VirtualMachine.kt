package com.example.test.domain

import java.time.LocalDate


data class VirtualMachine(
    var id: Int,
    var name: String,
    var projectId: Int,
    var project: Project? = null,
    var creatorId: Int,
    var creator: Member? = null,
    var state: State,
    var mode: Mode,
    var customerId: Int,
    var customer: Customer? = null,
    var hostname: String,
    var fqdn: String,
    var vCPUAmount: Int,
    var memoryAmount: Int,
    var storageAmount: Int,
    var requestDate: LocalDate,
    var beginDate: LocalDate,
    var endDate: LocalDate,
    var backupFrequency: Int,
    var availability: Availability,
    var highAvailability: Boolean,
    var hostServer: Int,
)