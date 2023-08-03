package com.example.test.screens.customers

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.CustomerMock
import com.example.test.domain.Customer
import com.example.test.network.VicApiStatus
import com.example.test.repository.CustomerRepository
import kotlinx.coroutines.launch

class ListCustomersViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = CustomerRepository(database)


    //Initialize data
    init {
        viewModelScope.launch {
            repository.refreshCustomers()
        }
    }
    val listCustomers = repository.customers

    fun check(): Boolean {
        Log.i("ListCustomers","PRINTING CUSTOMERS")
        listCustomers.value?.forEach {
            Log.i("ListCustomers", "name: ${it.name} || mail: ${it.email}")
        }
        return true
    }

    val amai = check()


}