package com.example.test.screens.members

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import kotlinx.coroutines.launch

class MemberViewModel(application: Application, val id: Int) : ViewModel() {

    //Code for Database and Repository



    //live data objects
    private val _member = MutableLiveData<Member>()
    val member: LiveData<Member>
        get() = _member

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _member.value = MemberMock().members[id]
        }
    }
}