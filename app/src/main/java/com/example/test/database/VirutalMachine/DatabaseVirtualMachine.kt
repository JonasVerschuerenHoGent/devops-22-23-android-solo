package com.example.test.database.VirutalMachine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.domain.Availability
import com.example.test.domain.Mode
import com.example.test.domain.State
import com.example.test.domain.VirtualMachine
import java.time.LocalDate

@Entity(tableName = "virtualmachine_table")
data class DatabaseVirtualMachine(

    @PrimaryKey
    var id: Int,

    @ColumnInfo(name = "vm_name")
    var name: String,

    @ColumnInfo(name = "vm_project_id")
    var projectId: Int,

    @ColumnInfo(name = "vm_creator_id")
    var creatorId: Int,

    @ColumnInfo(name = "vm_state")
    var state: String,

    @ColumnInfo(name = "vm_mode")
    var mode: String,

    @ColumnInfo(name = "vm_customer_id")
    var customerId : Int,

    @ColumnInfo(name = "vm_hostname")
    var hostname: String,

    @ColumnInfo(name = "vm_fqdn")
    var fqdn: String,

    @ColumnInfo(name = "vm_vcpu_amount")
    var vCPUAmount: Int,

    @ColumnInfo(name = "vm_memory_amount")
    var memoryAmount: Int,

    @ColumnInfo(name = "vm_storage_amount")
    var storageAmount: Int,

    @ColumnInfo(name = "vm_request_date")
    var requestDate: String,

    @ColumnInfo(name = "vm_begin_date")
    var beginDate: String,

    @ColumnInfo(name = "vm_end_date")
    var endDate: String,

    @ColumnInfo(name = "vm_backup_frequency")
    var backupFrequency: String,

    @ColumnInfo(name = "vm_availability")
    var availability: String,

    @ColumnInfo(name = "vm_high_availability")
    var highAvailability: Boolean,

    @ColumnInfo(name = "vm_host_server")
    var hostServer: String,
)

// Convert a single DatabaseCustomer into a normal domain Customer
fun DatabaseVirtualMachine.asDomainModel(): VirtualMachine {
    return VirtualMachine(
        id = id,
        name = name,
        projectId = projectId,
        creatorId = creatorId,
        state = asDomainState(state),
        mode = asDomainMode(mode),
        customerId = customerId,
        hostname = hostname,
        fqdn = fqdn,
        vCPUAmount = vCPUAmount,
        memoryAmount = memoryAmount,
        storageAmount = storageAmount,
        requestDate = LocalDate.parse(requestDate),
        beginDate = LocalDate.parse(beginDate),
        endDate = LocalDate.parse(endDate),
        backupFrequency = backupFrequency,
        availability = asDomainAvailability(availability),
        highAvailability = highAvailability,
        hostServer = hostServer,
    )
}

// Convert a list of DatabaseCustomers in a list of normal domain Customers
fun List<DatabaseVirtualMachine>.asDomainModel(): List<VirtualMachine> {
    return map { it.asDomainModel() }
}


// Mappings
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

