package com.example.test.screens.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListCustomersViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCustomersViewModel::class.java)) {
            return ListCustomersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}