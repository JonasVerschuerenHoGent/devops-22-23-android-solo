package com.example.test.screens.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import kotlinx.coroutines.launch

class MemberViewModel(val id: Int) : ViewModel() {

    //live data objects
    private val _member = MutableLiveData<Member>()
    val user: LiveData<Member>
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