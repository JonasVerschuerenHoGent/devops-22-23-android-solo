package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListUsersViewModel : ViewModel() {

    //live data objects
    private val _listUsers = MutableLiveData<Array<String>>()
    val listUsers: LiveData<Array<String>>
        get() = _listUsers

    init {
        _listUsers.value = arrayOf(
            "Jonas Verschueren",
            "Sean Van Den Branden",
            "Janne Van Schepdael",
            "Yorben Van Driessche",
            "Kadir Akkurt",
            "Miro"
        )
    }

}