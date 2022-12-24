package com.example.test.screens.users

import android.app.Application
import android.content.res.Resources.NotFoundException
import androidx.lifecycle.*
import com.example.test.AccountMock
import com.example.test.database.Account.CustomerDatabase
import com.example.test.database.Account.asDomainModel
import com.example.test.domain.Account
import com.example.test.network.ApiStatus
import com.example.test.network.asDomainModels
import com.example.test.network.interfaces.AccountApiService
import com.example.test.network.interfaces.ApiAccountObj
import com.example.test.repository.CustomerRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class UserViewModel(application: Application, id: Int) : AndroidViewModel(application) {

    //live data objects
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = CustomerDatabase.getInstance(application.applicationContext)

    private val _customer = MutableLiveData<Account>()
    val customer: LiveData<Account>
        get() = _customer

    private val _backupContact = MutableLiveData<Account>()
    val backupContact: LiveData<Account>
        get() = _backupContact


    init {
        Timber.i("Getting customer $id")

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                val tempCustomer = database.customerDatabaseDao.getCustomerById(id)
                    ?: throw NotFoundException("Customer $id was not found.")
                _customer.value = tempCustomer.asDomainModel()
                // Only gets one backupcontact to prevent infinite loops.
                val tempBackup =
                    database.customerDatabaseDao.getCustomerById(tempCustomer.backupContactId)
                        ?: throw NotFoundException("Customer ${tempCustomer.backupContactId} was not found.")
                _backupContact.value = tempBackup.asDomainModel()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Timber.e("Exception occurred while refreshing the customers", e)
                _status.value = ApiStatus.ERROR
            }
        }
    }

}