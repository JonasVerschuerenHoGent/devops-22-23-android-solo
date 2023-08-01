package com.example.test.database.VirutalMachine

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test.MainActivity
import com.example.test.database.VicDatabase
import com.example.test.database.customer.CustomerDatabaseDao
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.customer.asDomainModel
import com.example.test.database.member.DatabaseMember
import com.example.test.database.member.MemberDatabaseDao
import com.example.test.database.member.asDomainModel
import com.example.test.database.project.DatabaseProject
import com.example.test.database.project.ProjectDatabaseDao
import com.example.test.database.project.asDomainModel
import com.example.test.domain.*
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

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
    var backupFrequency: Int,

    @ColumnInfo(name = "vm_availability")
    var availability: String,

    @ColumnInfo(name = "vm_high_availability")
    var highAvailability: Boolean,

    @ColumnInfo(name = "vm_host_server")
    var hostServer: Int,
)

// Convert a single DatabaseCustomer into a normal domain Customer
fun DatabaseVirtualMachine.asDomainModel(): VirtualMachine {
    return VirtualMachine(
        id = id,
        name = name,
        projectId = projectId,
        creatorId = creatorId,
        state = state.asDomainState(),
        mode = mode.asDomainMode(),
        customerId = customerId,
        hostname = hostname,
        fqdn = fqdn,
        vCPUAmount = vCPUAmount,
        memoryAmount = memoryAmount,
        storageAmount = storageAmount,
        requestDate = asDomainDate(requestDate),
        beginDate = asDomainDate(beginDate),
        endDate = asDomainDate(endDate),
        backupFrequency = backupFrequency,
        availability = availability.asDomainAvailability(),
        highAvailability = highAvailability,
        hostServer = hostServer,
    )
}

// Convert a list of DatabaseCustomers in a list of normal domain Customers
fun List<DatabaseVirtualMachine>.asDomainModel(): List<VirtualMachine> {
    return map { it.asDomainModel() }
}


// Mappings
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

fun asDomainDate(date: String) : LocalDate {
    return LocalDate.parse(date)
}

