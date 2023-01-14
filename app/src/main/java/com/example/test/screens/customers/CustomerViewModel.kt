package com.example.test.screens.customers

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.customer.CustomerDatabase
import com.example.test.database.customer.asDomainModel
import com.example.test.domain.Customer
import com.example.test.network.ApiStatus
import kotlinx.coroutines.launch
import timber.log.Timber

class CustomerViewModel(application: Application, id: Int) : ViewModel() {
    //live data objects
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = CustomerDatabase.getInstance(application.applicationContext)

    private val _customer = MutableLiveData<Customer>()
    val customer: LiveData<Customer>
        get() = _customer

    private val _backupCustomer = MutableLiveData<Customer>()
    val backupCustomer: LiveData<Customer>
        get() = _backupCustomer

    private val _backupContact = MutableLiveData<Customer>()
    val backupContact: LiveData<Customer>
        get() = _backupContact


    init {
        Timber.i("Getting Member $id")

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                val tempCustomer = database.customerDatabaseDao.getCustomerById(id)
                    ?: throw Resources.NotFoundException("Member $id was not found.")
                _customer.value = tempCustomer.asDomainModel()

                    ?: throw Resources.NotFoundException("Member $id was not found.")

                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Timber.e("Exception occurred while refreshing the customers", e.message)
                _status.value = ApiStatus.ERROR
            }
        }
    }
}