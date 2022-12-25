//TODO rename user/account --> member
package com.example.test.screens.members

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MemberListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemberListViewModel::class.java)) {
            return MemberListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}