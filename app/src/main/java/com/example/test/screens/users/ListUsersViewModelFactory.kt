package com.example.test.screens.users

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListUsersViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListUsersViewModel::class.java)) {
            return ListUsersViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}