package com.example.test.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.AccountMock
import com.example.test.domain.Account
import kotlinx.coroutines.launch

class UserViewModel(val id: Int) : ViewModel() {

    //live data objects
    private val _user = MutableLiveData<Account>()
    val user: LiveData<Account>
        get() = _user

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _user.value = AccountMock().users[id]
        }
    }
}