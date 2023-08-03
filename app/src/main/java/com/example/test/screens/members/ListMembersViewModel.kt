package com.example.test.screens.members

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import com.example.test.network.VicApiStatus
import com.example.test.repository.CustomerRepository
import com.example.test.repository.MemberRepository
import kotlinx.coroutines.launch

class ListMembersViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = MemberRepository(database)

    private var _filter = FilterHolder()

    init {
        viewModelScope.launch {
            repository.refreshMembers()
        }
    }
    val listMembers = repository.members

    private val _filteredMembers = MutableLiveData<List<Member>>()
    val filteredMembers: LiveData<List<Member>>
        get() = _filteredMembers

    fun onFilterChanged(filter: String, isChecked: Boolean) {
        doFilter()
        if (this._filter.update(filter, isChecked)) {
            _filteredMembers.value = listMembers.value?.filter { m ->
                m.role.toString() == _filter.currentValue
            }
        } else {
            //Fetch the members from the database
            doFilter()
        }
    }

    fun doFilter() {
        _filteredMembers.value = listMembers.value
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