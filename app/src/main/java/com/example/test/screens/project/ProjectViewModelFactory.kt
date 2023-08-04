package com.example.test.screens.project

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test.screens.members.MemberViewModel

class ProjectViewModelFactory (
    private val application: Application,
    private val id: Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (!modelClass.isAssignableFrom(ProjectViewModel::class.java)) throw IllegalArgumentException(
            "Unknown ViewModel class")
        return ProjectViewModel(application, id) as T
    }

}