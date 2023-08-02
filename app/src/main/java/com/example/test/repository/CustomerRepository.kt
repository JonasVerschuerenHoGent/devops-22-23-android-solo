package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.VicDatabase
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.network.NetworkCustomer
import com.example.test.network.NetworkCustomerContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerRepository(private val database: VicDatabase) {

    //Customers attribute that can be shown on screen.
    val customers: LiveData<List<Customer>> = Transformations.map(database.customerDatabaseDao.getAllCustomers()) {
        it.asDomainModel()
    }

//    fun customer(id: Int): LiveData<Customer> {
//        val dc: LiveData<DatabaseCustomer>? = database.customerDatabaseDao.getCustomerById(id)
//        val c: LiveData<Customer> = Transformations.map(dc) { it.asDomainModel() }
//        return c
//    }


    //Refreshes the Customers
    suspend fun refreshCustomers() {
        withContext(Dispatchers.IO) {
            val detailedCustomers = ArrayList<NetworkCustomer>()
            val customers = VicApi.retrofitService.getAllCustomers().await()
            customers.forEach {
                val c = VicApi.retrofitService.getCustomerByid(it.id).await()
                detailedCustomers.add(c)
            }
            database.customerDatabaseDao.insertAll(*NetworkCustomerContainer(detailedCustomers).asDatabaseModel())
        }
    }


}