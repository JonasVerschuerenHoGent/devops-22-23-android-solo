package com.example.test.screens.project

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.database.VicDatabase
import com.example.test.domain.Project
import com.example.test.domain.ProjectMock
import com.example.test.network.VicApiStatus
import com.example.test.repository.MemberRepository
import com.example.test.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectListViewModel(application: Application) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = ProjectRepository(database)


    init {
        viewModelScope.launch {
            repository.refreshProjects()
        }
    }
    val listProjects = repository.projects

}