package com.example.test.screens.virtual_machines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import kotlinx.coroutines.launch

class VirtualMachineViewModel(val id: Int) : ViewModel() {

    //live data objects
    private val _vms = MutableLiveData<VirtualMachine>()
    val vm: LiveData<VirtualMachine>
        get() = _vms

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _vms.value = VirtualMachineMock().virtualMachines[id]
        }
    }
}