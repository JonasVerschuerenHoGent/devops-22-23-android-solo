package com.example.test.screens.customers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.CustomerMock
import com.example.test.domain.Customer
import com.example.test.network.VicApiService
import com.example.test.network.VicApiStatus
import com.example.test.repository.CustomerRepository
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application, val id: Int) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = CustomerRepository(database)

    //live data object
    val customer = repository.customer
    val listVms = repository.getVirtualMachinesOfCustomer(id)


    init {
        repository.customerId = id
    }







}