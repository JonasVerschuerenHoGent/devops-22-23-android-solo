package com.example.test.screens.virtual_machines

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VirtualMachineViewModelFactory(private val application : Application, private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(!modelClass.isAssignableFrom(VirtualMachineViewModel::class.java)) throw IllegalArgumentException("Unknown ViewModel class")
        return VirtualMachineViewModel(application, id) as T
    }
}
