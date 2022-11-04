package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.Account

class UserViewModel : ViewModel() {

    //live data objects
    private val _user = MutableLiveData<Account>()
    val user: LiveData<Account>
        get() = _user

    init {

    }

}