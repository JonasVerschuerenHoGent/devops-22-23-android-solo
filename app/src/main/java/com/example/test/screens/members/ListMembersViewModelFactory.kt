package com.example.test.screens.members

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListMembersViewModelFactory(private val application: Application):  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListMembersViewModel::class.java)) {
            return ListMembersViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}