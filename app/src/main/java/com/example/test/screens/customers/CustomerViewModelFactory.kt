package com.example.test.screens.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomerViewModelFactory(private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(!modelClass.isAssignableFrom(CustomerViewModel::class.java)) throw IllegalArgumentException("Unknown ViewModel class")
        return CustomerViewModel(id) as T
    }
}
