package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.test.database.VicDatabase
import com.example.test.database.VirutalMachine.asDomainModel
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.domain.VirtualMachine
import com.example.test.network.NetworkCustomer
import com.example.test.network.NetworkCustomerContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.stream.Collectors.toList

class CustomerRepository(private val database: VicDatabase) {
    var customerId: Int? = -1

    //Customers attribute that can be shown on screen.
    val customers: LiveData<List<Customer>> = Transformations.map(database.customerDatabaseDao.getAllCustomers()) {
        it.asDomainModel()
    }

    val customer: LiveData<Customer> = Transformations.map(database.customerDatabaseDao.getCustomerById(customerId!!)) {
        it.asDomainModel()
    }

    fun getVirtualMachinesOfCustomer(id: Int): LiveData<List<VirtualMachine>> {
        return database.virtualMachineDatabaseDao.getVmsFromCustomerId(id)!!.map { it.asDomainModel() }
    }



    //Refreshes the Customers
    suspend fun refreshCustomers() {
        withContext(Dispatchers.IO) {
            val detailedCustomers = ArrayList<NetworkCustomer>()
            val customersWrapper = VicApi.retrofitService.getAllCustomers().await()
            customersWrapper.customers.forEach {
                val c = VicApi.retrofitService.getCustomerByid(it.id).await()
                detailedCustomers.add(c.customer)
            }
            database.customerDatabaseDao.insertAll(*NetworkCustomerContainer(detailedCustomers).asDatabaseModel())
        }
    }


}