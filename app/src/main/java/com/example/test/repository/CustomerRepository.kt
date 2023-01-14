package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.customer.CustomerDatabase
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.network.ApiAccountContainer
import com.example.test.network.asDatabaseCustomer
import com.example.test.network.interfaces.ApiCustomerObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.logging.Logger

class CustomerRepository(private val database : CustomerDatabase) {
    // LiveData object with Actors from the database transformed to domain Customers

    val customers: LiveData<List<Customer>> = Transformations.map(database.customerDatabaseDao.getAllCustomersLive()) {
        it.asDomainModel()
    }

    // Network call to refresh the Customers
    suspend fun refreshCustomers() {
        // Switch the context to an IO thread
        Timber.i("TEST--------------------222")
        Logger.getLogger(CustomerRepository::class.java.name).warning("TEST------------------..")
        withContext(Dispatchers.IO) {
            val apiCustomer = ApiCustomerObj.retrofitService.getAllCustomers().await()
            Timber.i(apiCustomer.customers[0].toString())
            Logger.getLogger(CustomerRepository::class.java.name).warning("TEST------------------..")
            val apiAccountContainer = ApiAccountContainer(apiCustomer.customers)
            // '*': Kotlin spread operator, used for functions that expect a vararg param and this just spreads the array into separate fields
            database.customerDatabaseDao.insertAll(*apiAccountContainer.asDatabaseCustomer().toTypedArray())
//            database.customerDatabaseDao.insertAll(*apiCustomer.asDatabaseCustomer())
            Timber.i("TEST-------------------------------------------------------"+apiCustomer.customers[0].name)
            Timber.i("Refreshed customers from the API and saved them in the database")
        }
    }
}
