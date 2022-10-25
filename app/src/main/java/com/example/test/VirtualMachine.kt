package com.example.test

import java.util.Random

data class VirtualMachine(
    var name: String,
    var id: Int,
    var hostname: String,
    var fqdn: String,
    var mode: Mode,
    var template: Template,
    var backup: Int,
    var availability: Availability,
    var host: Int,
    var cluster: Int,
    var ports: IntArray,
    var state: State,
    var vCPUAmount: Int,
    var memoryAmount: Int,
    var storageAmount: Int
)