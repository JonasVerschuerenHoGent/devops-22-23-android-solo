package com.example.test.screens.virtual_machines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VirtualMachineListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VirtualMachineListViewModel::class.java)) {
            return VirtualMachineListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}