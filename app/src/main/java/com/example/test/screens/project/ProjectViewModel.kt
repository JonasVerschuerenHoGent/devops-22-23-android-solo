package com.example.test.screens.project

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.test.database.VicDatabase
import com.example.test.repository.ProjectRepository

class ProjectViewModel(application: Application, id: Int) : ViewModel() {

    //Code for Database and Repository
    private val database = VicDatabase.getInstance(application)
    private val repository = ProjectRepository(database)

    //live data objects
    val project = repository.getProjectById(id)
    val listVms = repository.getVirtualMachinesOfProjects(id)


    val textToPutOnScreen = "GB"


}