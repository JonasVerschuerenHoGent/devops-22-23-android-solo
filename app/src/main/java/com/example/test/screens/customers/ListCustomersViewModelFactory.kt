package com.example.test.screens.customers

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListCustomersViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListCustomersViewModel::class.java)) {
            return ListCustomersViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}