package com.example.test.screens.customers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.CustomerMock
import com.example.test.domain.Customer
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application, val id: Int) : ViewModel() {

    //Code for Database and Repository


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