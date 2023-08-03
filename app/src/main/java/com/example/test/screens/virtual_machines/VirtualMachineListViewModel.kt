package com.example.test.screens.virtual_machines

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import com.example.test.network.VicApiStatus
import com.example.test.repository.CustomerRepository
import com.example.test.repository.VirtualMachineRepository
import kotlinx.coroutines.launch

class VirtualMachineListViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = VirtualMachineRepository(database)


    //Initialize data
    init {
        viewModelScope.launch {
            repository.refreshVirtualMachines()
        }
    }
    val listVms = repository.virtualMachines


}