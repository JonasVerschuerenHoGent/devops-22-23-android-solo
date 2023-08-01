package com.example.test

import androidx.room.Database
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.test.database.VicDatabase
import com.example.test.database.VirutalMachine.DatabaseVirtualMachine
import com.example.test.database.VirutalMachine.VirtualMachineDatabaseDao
import com.example.test.database.VirutalMachine.asDomainModel
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.customer.CustomerDatabaseDao
import com.example.test.database.customer.asDomainModel
import com.example.test.database.member.DatabaseMember
import com.example.test.database.member.MemberDatabaseDao
import com.example.test.database.member.asDomainModel
import com.example.test.database.project.DatabaseProject
import com.example.test.database.project.ProjectDatabaseDao
import com.example.test.domain.*
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.time.LocalDate


@RunWith(AndroidJUnit4::class)
class VicDatabaseTest {

    private lateinit var customerDao: CustomerDatabaseDao
    private lateinit var memberDao: MemberDatabaseDao
    private lateinit var projectDao: ProjectDatabaseDao
    private lateinit var vmDao: VirtualMachineDatabaseDao
    private lateinit var db: VicDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, VicDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        customerDao = db.customerDatabaseDao
        memberDao = db.memberDatabaseDao
        projectDao = db.projectDatabaseDao
        vmDao = db.virtualMachineDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    //Test Customer
    @Test
    @Throws(Exception::class)
    fun insertAndGetCustomer() {
        val customer = DatabaseCustomer(
            id = 1,
            name = "Jonas",
            email = "jonas@outlook.com",
            phoneNr = "+32 494 58 01 87",
            department = 2,
            externType = "unknown",
            education = "Toegepaste Informatica",
            backupContactId = 1,
        )
        customerDao.insert(customer)

        val cust = customerDao.getCustomerById(1)
        assertEquals(cust?.name,"Jonas")
        assertEquals(cust?.email,"jonas@outlook.com")
        assertEquals(cust?.department,2)

        val realCust: Customer = cust!!.asDomainModel()
        assertEquals(realCust.department, Department.DBT)
    }

    //Test Member
    @Test
    @Throws(Exception::class)
    fun insertAndGetMember() {
        val member = DatabaseMember(
            id = 1,
            name = "Jonas",
            active = true,
            email = "jonas@outlook.com",
            phoneNr = "+32 494 58 01 87",
            role = 1,
            department = 5,
        )
        memberDao.insert(member)

        val mem = memberDao.getMemberById(1)
        assertEquals(mem?.name,"Jonas")

        val realMem: Member = mem!!.asDomainModel()
        assertEquals(realMem.department, Department.DLO)
        assertEquals(realMem.role, Role.Admin)
    }


    //Test Project
    @Test
    @Throws(Exception::class)
    fun insertAndGetProject() {
        val project = DatabaseProject(
            id = 1,
            name = "Test project",
            customerId = 1,
            state = "Ongoing"
        )
        projectDao.insert(project)

        val proj = projectDao.getProjectById(1)
        assertEquals(proj?.name,"Test project")
        assertEquals(proj?.state,"Ongoing")
    }

    //Test Virtual Machine
    @Test
    @Throws(Exception::class)
    fun insertAndGetVm() {
        val vm = DatabaseVirtualMachine(
            id = 1,
            name = "The first VM",
            projectId = 1,
            creatorId = 1,
            state = State.InProgress.toString(),
            mode = Mode.IAAS.toString(),
            customerId = 1,
            hostname = "HOSTNAME-1",
            fqdn = "www.vic.com",
            vCPUAmount = 4,
            memoryAmount = 16,
            storageAmount = 512,
            requestDate = giveDateWithDayOffset(-10).toString(),
            beginDate = giveDateWithDayOffset(-5).toString(),
            endDate = giveDateWithDayOffset(1).toString(),
            backupFrequency = 7,
            availability = Availability.ALWAYS.toString(),
            highAvailability = true,
            hostServer = 10,
        )
        vmDao.insert(vm)
        val v = vmDao.getVirtualMachineById(1)
        assertEquals(v?.name,"The first VM")
        assertEquals(v?.state,"InProgress")
        assertEquals(v?.backupFrequency,7)

        val realVm: VirtualMachine = v!!.asDomainModel()
        assertEquals(realVm.state, State.InProgress)
        assertEquals(realVm.mode, Mode.IAAS)
        assertEquals(realVm.availability, Availability.ALWAYS)
    }



    //Helper functions
    private fun giveDateWithDayOffset(offset: Long): LocalDate {
        return LocalDate.now().plusDays(offset)
    }

}

