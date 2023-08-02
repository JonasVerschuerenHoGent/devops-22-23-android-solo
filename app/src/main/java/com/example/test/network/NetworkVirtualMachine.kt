package com.example.test.network

import com.example.test.domain.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate


data class NetworkVirtualMachine(
    val id: Int,
    val name : String,
    val state: String,
    @Json(name = "modus") val mode: String,
    val hostname: String,
    val fqdn: String,
    @Json(name = "vcpUamount") val vCPUAmount: Int,
    val memoryAmount: Int,
    val storageAmount: Int,
    val requestDate: String,
    @Json(name = "startDate") val beginDate: String,
    val endDate: String,
    val backupFrequency: String,
    val availability: String,
    val highAvailability: Boolean,
    @Json(name = "fysiekeServer") val hostServer: String,
    val projectId: Int,
    val creatorId: Int,
    val customerId: Int,
)


//Convert network to domain
@JsonClass(generateAdapter = true)
data class NetworkVmContainer(val vms: List<NetworkVirtualMachine>)

fun NetworkVmContainer.asDomainModel(): List<VirtualMachine> {
    return vms.map {
        VirtualMachine(
            id = it.id,
            name = it.name,
            projectId = it.projectId,
            creatorId = it.creatorId,
            state = asDomainState(it.state),
            mode = asDomainMode(it.mode),
            customerId = it.customerId,
            hostname = it.hostname,
            fqdn = it.fqdn,
            vCPUAmount = it.vCPUAmount,
            memoryAmount = it.memoryAmount,
            storageAmount = it.storageAmount,
            requestDate = LocalDate.parse(it.requestDate),
            beginDate = LocalDate.parse(it.beginDate),
            endDate = LocalDate.parse(it.endDate),
            backupFrequency = it.backupFrequency,
            availability = asDomainAvailability(it.availability),
            highAvailability = it.highAvailability,
            hostServer = Integer.parseInt(it.hostServer),
        )
    }
}

fun asDomainState(stateString: String) : State {
    when(stateString.lowercase()) {
        "requested" -> return State.Requested
        "processing" -> return State.InProgress
        "denied" -> return State.Denied
        else -> return State.Accepted
    }
}
fun asDomainMode(modeString: String) : Mode {
    when(modeString.lowercase()) {
        "paas" -> return Mode.PAAS
        "iaas" -> return Mode.IAAS
        "saas" -> return Mode.SAAS
        "sjabloon1" -> return Mode.Sjabloon1
        "sjabloon2" -> return Mode.Sjabloon2
        "ai" -> return Mode.AI
        "ms_sql" -> return Mode.MS_SQL
        "postgresql" -> return Mode.PostgreSQL
        "mongodb" -> return Mode.MongoDB
        else -> return Mode.MySQL
    }
}
fun asDomainAvailability(availabilityString: String) : Availability {
    when(availabilityString.lowercase()) {
        "business hours" -> return Availability.BUSINESS_HOURS
        else -> return Availability.ALWAYS
    }
}






