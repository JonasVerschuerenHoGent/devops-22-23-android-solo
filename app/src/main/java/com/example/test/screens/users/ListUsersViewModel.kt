package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.AccountMock
import com.example.test.domain.Account
import kotlinx.coroutines.launch

class ListUsersViewModel : ViewModel() {

    //live data objects
    private val _listAccount = MutableLiveData<List<Account>>()
    val listUsers: LiveData<List<Account>>
        get() = _listAccount

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _listAccount.value = AccountMock().users
        }
    }

}