package com.example.test.screens.members

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListMembersViewModelFactory:  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListMembersViewModel::class.java)) {
            return ListMembersViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}