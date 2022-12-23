package com.example.test.screens.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import kotlinx.coroutines.launch

class ListMembersViewModel : ViewModel() {
    private val _listMembers = MutableLiveData<List<Member>>()
    val listMembers: LiveData<List<Member>>
        get() = _listMembers

    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _listMembers.value = MemberMock().members
        }
    }
}