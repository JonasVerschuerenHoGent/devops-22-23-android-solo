package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.VicDatabase
import com.example.test.database.project.asDomainModel
import com.example.test.domain.Project
import com.example.test.network.NetworkProjectContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository(private val database: VicDatabase) {

    //Projects attribute that can be shown on screen.
    val projects: LiveData<List<Project>> = Transformations.map(database.projectDatabaseDao.getAllProjects()) {
        it.asDomainModel()
    }


    //Refreshes the Projects
    suspend fun refreshProjects() {
        withContext(Dispatchers.IO) {
            val detailedProjects = VicApi.retrofitService.getAllProjects().await()
            database.projectDatabaseDao.insertAll(*NetworkProjectContainer(detailedProjects).asDatabaseModel())
        }
    }


}