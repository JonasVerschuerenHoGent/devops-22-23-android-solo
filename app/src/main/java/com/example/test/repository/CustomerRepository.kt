package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.Account.CustomerDatabase
import com.example.test.database.Account.asDomainModel
import com.example.test.domain.Account
import com.example.test.network.ApiAccount
import com.example.test.network.asDatabaseCustomer
import com.example.test.network.asDomainModels
import com.example.test.network.interfaces.ApiAccountObj
import com.example.test.network.interfaces.ApiAccountObj.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class CustomerRepository(private val database : CustomerDatabase) {
    // LiveData object with Actors from the database transformed to domain Customers
    val customers: LiveData<List<Account>> = Transformations.map(database.customerDatabaseDao.getAllCustomersLive()) {
        it.asDomainModel()
    }

    // Network call to refresh the Customers
    suspend fun refreshCustomers() {
        // Switch the context to an IO thread
        withContext(Dispatchers.IO) {
            val apiAccount = ApiAccountObj.retrofitService.getAllAccounts().await()
            // '*': Kotlin spread operator, used for functions that expect a vararg param and this just spreads the array into separate fields
            database.customerDatabaseDao.insertAll(*apiAccount.asDatabaseCustomer())

            Timber.i("Refreshed customers from the API and saved them in the database")
        }
    }
}
