package com.example.test.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListUsersViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListUsersViewModel::class.java)) {
            return ListUsersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}