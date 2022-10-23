package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListUsersViewModel : ViewModel() {

    //The testdata for users
    private var listUsers: MutableList<String>

    //live data objects
    private val _usersListView = MutableLiveData<String>()
    val usersListView: LiveData<String>
        get() = _usersListView


    init {
        listUsers = mutableListOf(
            "Jonas Verschueren",
            "Sean Van Den Branden",
            "Janne Van Schepdael",
            "Yorben Van Driessche",
            "Kadir Akkurt",
            "Miro"
        )
    }

}