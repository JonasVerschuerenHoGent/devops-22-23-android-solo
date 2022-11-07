package com.example.test.domain

import java.util.Date

data class VirtualMachine(
    var name: String,
    var id: Int,
    var hostname: String,
    var fqdn: String,
    var mode: Mode,
    var backupFrequency: Int,
    var availability: Availability,
    var hostServer: Int,
    var cluster: Int,
    var ports: IntArray,
    var state: State,
    var vCPUAmount: Int,
    var memoryAmount: Int,
    var storageAmount: Int,
    var highAvailability: Boolean,
    var requestDate: Date,
    var beginDate: Date,
    var endDate: Date
)