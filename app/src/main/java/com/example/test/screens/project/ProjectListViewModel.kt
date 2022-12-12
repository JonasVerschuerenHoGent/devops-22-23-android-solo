package com.example.test.screens.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Project
import com.example.test.domain.ProjectMock
import kotlinx.coroutines.launch

class ProjectListViewModel : ViewModel() {

    //live data objects
    private val _listProject = MutableLiveData<List<Project>>()
    val listProjects: LiveData<List<Project>>
    get() = _listProject
    init {
        initializeLiveData()
    }

    private fun initializeLiveData(){
        viewModelScope.launch {
            _listProject.value = ProjectMock().projects
        }
    }
}