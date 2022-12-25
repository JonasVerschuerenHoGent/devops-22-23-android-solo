package com.example.test.screens.project

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ProjectListViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(!modelClass.isAssignableFrom(ProjectListViewModel::class.java)) throw IllegalArgumentException("Unknown ViewModel class")
        return ProjectListViewModel() as T
    }
}