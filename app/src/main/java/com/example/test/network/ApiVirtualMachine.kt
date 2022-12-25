package com.example.test.network

import com.example.test.database.VirutalMachine.DatabaseVirtualMachine
import com.example.test.domain.*
import com.squareup.moshi.Json
import java.time.LocalDate

data class ApiVirtualMachine(

    @Json(name = "virtual_machine_name")
    var name: String,
    @Json(name = "id")
    var id: Int,
    @Json(name = "hostname")
    var hostname: String,
    @Json(name = "fqdn")
    var fqdn: String,
    @Json(name = "mode")
    var mode: String,
    @Json(name = "backup_frequency")
    var backupFrequency: Int,
    @Json(name = "availability")
    var availability: String,
    @Json(name = "host_server")
    var hostServer: Int,
    @Json(name = "cluster")
    var cluster: Int,
    @Json(name = "ports")
    var ports: IntArray,
    @Json(name = "State")
    var state: String,
    @Json(name = "vcpu_amount")
    var vCPUAmount: Int,
    @Json(name = "memory_amount")
    var memoryAmount: Int,
    @Json(name = "storage_amount")
    var storageAmount: Int,
    @Json(name = "high_availability")
    var highAvailability: Boolean,
    @Json(name = "request_date")
    var requestDate: String,
    @Json(name = "begin_date")
    var beginDate: String,
    @Json(name = "end_date")
    var endDate: String
)

fun ApiVirtualMachine.asDomainVirtualMachine(): VirtualMachine {
    return VirtualMachine(
        id = id,
        name = name,
        hostname = hostname,
        fqdn = fqdn,
        mode = mode.asDomainMode(),
        backupFrequency = backupFrequency,
        availability = availability.asDomainAvailability(),
        hostServer = hostServer,
        cluster = cluster,
        state = state.asDomainState(),
        vCPUAmount = vCPUAmount,
        memoryAmount = memoryAmount,
        storageAmount = storageAmount,
        highAvailability = highAvailability,
        requestDate = requestDate.asDomainDate(requestDate),
        beginDate = beginDate.asDomainDate(beginDate),
        endDate = endDate.asDomainDate(endDate)
    )
}

fun ApiVirtualMachine.asDatabaseVirtualMachine(): DatabaseVirtualMachine {
    return DatabaseVirtualMachine(
        id = id,
        name = name,
        hostname = hostname,
        fqdn = fqdn,
        mode = mode,
        backupFrequency = backupFrequency,
        availability = availability,
        hostServer = hostServer,
        cluster = cluster,
        state = state,
        vCPUAmount = vCPUAmount,
        memoryAmount = memoryAmount,
        storageAmount = storageAmount,
        highAvailability = highAvailability,
        requestDate = requestDate,
        beginDate = beginDate,
        endDate = endDate,
    )
}

// Container that helps us parsing the api response into multiple domain VirtualMachines
data class ApiVirtualMachineContainer(
    @Json(name = "body")
    val apiVirtualMachine : List<ApiVirtualMachine>
)

// Convert network result into domain VirtualMachines
fun ApiVirtualMachineContainer.asDomainModels(): List<VirtualMachine> {
    return apiVirtualMachine.map { it.asDomainVirtualMachine() }
}

fun List<ApiVirtualMachine>.asDomainModels(): List<VirtualMachine> {
    return this.map { it.asDomainVirtualMachine() }
}

// Convert a list of ApiVirtualMachines into a list of DatabaseVirtualMachines (this can then be used in the insert call as vararg)
fun List<ApiVirtualMachine>.asDatabaseVirtualMachine(): Array<DatabaseVirtualMachine> {
    return map { it.asDatabaseVirtualMachine() }.toTypedArray()
}


// Convert network result into DatabaseVirtualMachine
fun ApiVirtualMachineContainer.asDatabaseVirtualMachine(): List<DatabaseVirtualMachine> {
    return apiVirtualMachine.map { it.asDatabaseVirtualMachine() }
}


fun String.asDomainMode() : Mode {
    if (lowercase().equals("iaas")) {
        return Mode.IAAS
    } else if (lowercase().equals("ai")) {
        return Mode.AI
    } else if (lowercase().equals("ms_sql")) {
        return Mode.MS_SQL
    } else if (lowercase().equals("mysql")) {
        return Mode.MySQL
    } else if (lowercase().equals("postgresql")) {
        return Mode.PostgreSQL
    }
    return Mode.MongoDB

}

fun String.asDomainAvailability() : Availability {
    if (lowercase().equals("business_hours")) {
        return Availability.BUSINESS_HOURS
    }
    return Availability.ALWAYS

}

fun String.asDomainState() : State {
    if (lowercase().equals("requested")) {
        return State.Requested
    } else if (lowercase().equals("inprogress")) {
        return State.InProgress
    } else if (lowercase().equals("denied")) {
        return State.Denied
    }
    return State.Accepted

}

fun String.asDomainDate(date: String) : LocalDate {
    return LocalDate.parse(date)

}