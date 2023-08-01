package com.example.test.screens.members

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import com.example.test.domain.Role
import kotlinx.coroutines.launch

class ListMembersViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository



    private var _filter = FilterHolder()

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

    fun onFilterChanged(filter: String, isChecked: Boolean) {
        if (this._filter.update(filter, isChecked)) {
            //Fetch the members from the database
            val members = MemberMock().members
            _listMembers.value = members.filter { m ->
                m.role.toString() == _filter.currentValue
            }
        } else {
            //Fetch the members from the database
            _listMembers.value = MemberMock().members
        }
    }

    private class FilterHolder {
        var currentValue: String? = null
            private set

        fun update(changedFilter: String, isChecked: Boolean): Boolean {
            if (isChecked) {
                currentValue = changedFilter
                return true
            } else if (currentValue == changedFilter) {
                currentValue = null
                return false
            }
            return false
        }
    }
}