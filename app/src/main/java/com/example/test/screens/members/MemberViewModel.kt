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

class MemberViewModel(application: Application, val id: Int) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = MemberRepository(database)

    //live data objects
    val member = repository.getMemberById(id)
    val listVms = repository.getVirtualMachinesOfMember(id)


}