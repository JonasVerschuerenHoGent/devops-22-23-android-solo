package com.example.test.screens.customers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.AccountMock
import com.example.test.domain.Account
import kotlinx.coroutines.launch

class CustomerViewModel(val id: Int) : ViewModel() {

    //live data objects
    private val _customer = MutableLiveData<Account>()
    val customer: LiveData<Account>
        get() = _customer

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _customer.value = AccountMock().customers[id]
        }
    }
}