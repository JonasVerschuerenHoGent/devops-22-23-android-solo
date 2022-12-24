package com.example.test.screens.virtual_machines

import android.app.Application
import androidx.lifecycle.*
import com.example.test.database.Account.CustomerDatabase
import com.example.test.database.VirutalMachine.VirtualMachineDatabase
import com.example.test.domain.Account
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import com.example.test.network.ApiStatus
import com.example.test.network.asDomainModels
import com.example.test.network.interfaces.ApiAccountObj
import com.example.test.network.interfaces.ApiVirtualMachineObj
import com.example.test.repository.CustomerRepository
import com.example.test.repository.VirtualMachineRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.await
import timber.log.Timber

class VirtualMachineListViewModel(application: Application) : AndroidViewModel(application) {

    //live data objects
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = VirtualMachineDatabase.getInstance(application.applicationContext)
    private val virtualMachineRepository = VirtualMachineRepository(database)

    val customers = virtualMachineRepository.virtualmachines

    init {
        Timber.i("Refreshing the customers")

        viewModelScope.launch {
            _status.value = ApiStatus.LOADING

            try {
                virtualMachineRepository.refreshVirtualMachines()
                _status.value = ApiStatus.DONE
            } catch (e: Exception) {
                Timber.e("Exception occurred while refreshing the customers", e)
                _status.value = ApiStatus.ERROR
            }
        }
    }


}