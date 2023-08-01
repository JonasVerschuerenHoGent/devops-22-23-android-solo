package com.example.test.screens.customers

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.CustomerMock
import com.example.test.domain.Customer
import kotlinx.coroutines.launch

class ListCustomersViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository


    //live data objects
    private val _listCustomers = MutableLiveData<List<Customer>>()
    val listCustomers: LiveData<List<Customer>>
        get() = _listCustomers

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _listCustomers.value = CustomerMock().customers
        }
    }

}