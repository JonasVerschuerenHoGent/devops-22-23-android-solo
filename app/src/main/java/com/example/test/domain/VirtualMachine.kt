package com.example.test.domain

import java.time.LocalDateTime


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
    var requestDate: LocalDateTime,
    var beginDate: LocalDateTime,
    var endDate: LocalDateTime,
    var backupFrequency: String,
    var availability: Availability,
    var highAvailability: Boolean,
    var hostServer: String,
)