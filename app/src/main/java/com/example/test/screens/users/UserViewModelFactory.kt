package com.example.test.screens.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserViewModelFactory(private val id: Int): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(!modelClass.isAssignableFrom(UserViewModel::class.java)) throw IllegalArgumentException("Unknown ViewModel class")
        return UserViewModel(id) as T
    }
}
