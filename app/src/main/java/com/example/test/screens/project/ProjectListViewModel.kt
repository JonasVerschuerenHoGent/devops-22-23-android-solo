package com.example.test.screens.project

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.domain.Project
import com.example.test.network.ApiStatus
import com.example.test.network.asDomainModels
import com.example.test.network.interfaces.ApiProjectObj
import com.example.test.network.interfaces.ProjectApiService
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber

class ProjectListViewModel() : ViewModel() {




    //live data objects
    private val _listProject = MutableLiveData<List<Project>>()
    val listProjects: LiveData<List<Project>>
    get() = _listProject
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        Timber.i("Calling the API to get all Projects...")

        viewModelScope.launch {
            getProjectsFromApi()

        }
    }

    private suspend fun getProjectsFromApi() {
        _status.value = ApiStatus.LOADING

        try {
            val response = ApiProjectObj.retrofitService.getProjects().await()

            _status.value = ApiStatus.DONE
            //_listProject.value = response.asDomainModels()
        } catch (e: Exception) {
            Timber.e("Exception occurred while retrieving the Projects from the API", e)
            _status.value = ApiStatus.ERROR
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}