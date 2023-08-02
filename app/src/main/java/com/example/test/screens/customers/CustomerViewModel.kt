package com.example.test.screens.customers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.CustomerMock
import com.example.test.domain.Customer
import com.example.test.network.VicApiService
import com.example.test.network.VicApiStatus
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application, val id: Int) : ViewModel() {

    //Code for Database and Repository
    private val _status = MutableLiveData<VicApiStatus>()
    val status: LiveData<VicApiStatus>
        get() = _status

    //live data objects
    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer>
        get() = _customer

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _customer.value = CustomerMock().customers[id]
        }
    }
}