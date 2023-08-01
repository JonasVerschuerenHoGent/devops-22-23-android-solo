package com.example.test.screens.virtual_machines

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import kotlinx.coroutines.launch

class VirtualMachineListViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository



    //live data objects
    private val _listVM = MutableLiveData<List<VirtualMachine>>()
    private val  mock = VirtualMachineMock()
    val listVms: LiveData<List<VirtualMachine>>
        get() = _listVM

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _listVM.value = mock.virtualMachines
        }
    }

}