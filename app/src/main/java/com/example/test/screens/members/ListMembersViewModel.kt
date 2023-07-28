package com.example.test.screens.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import com.example.test.domain.Role
import kotlinx.coroutines.launch

class ListMembersViewModel : ViewModel() {

//    private var _filter = FilterHolder()

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

//    fun onFilterChanged(filter: Role, isChecked: Boolean) {
//        if (this._filter.update(filter, isChecked)) {
//
//            val members: List<Member> = MemberMock().members
//            _listMembers.value = members.map { m ->
//                if(m.role == _filter.currentValue)
//                    return m
//            }
//
//        } else {
//            _listMembers.value = MemberMock().members
//        }
//    }


//    private class FilterHolder {
//        var currentValue: Role? = null
//            private set
//
//        fun update(changedFilter: Role, isChecked: Boolean): Boolean {
//            if (isChecked) {
//                currentValue = changedFilter
//                return true
//            } else if (currentValue == changedFilter) {
//                currentValue = null
//                return true
//            }
//            return false
//        }
//    }


}