package com.example.test.screens.users

import android.app.Application
import androidx.lifecycle.*
import com.example.test.database.Account.CustomerDatabase
import com.example.test.domain.Account
import com.example.test.network.ApiStatus
import com.example.test.network.asDomainModels
import com.example.test.network.interfaces.ApiAccountObj.retrofitService
import com.example.test.repository.CustomerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class ListUsersViewModel(application: Application) : AndroidViewModel(application) {

    //live data objects
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = CustomerDatabase.getInstance(application.applicationContext)
    private val customerRepository = CustomerRepository(database)

    val customers = customerRepository.customers

    init {
        Timber.i("Refreshing the customers")

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                customerRepository.refreshCustomers()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Timber.e("Exception occurred while refreshing the customers", e)
                _status.value = ApiStatus.ERROR
            }
        }
    }

}