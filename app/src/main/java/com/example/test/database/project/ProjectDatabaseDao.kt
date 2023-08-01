package com.example.test.database.project

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.database.VirutalMachine.DatabaseVirtualMachine
import com.example.test.domain.VirtualMachine

@Dao
interface ProjectDatabaseDao {

    @Insert
    fun insert(project: DatabaseProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg projects: DatabaseProject)

    @Query("SELECT * FROM project_table ORDER BY id DESC")
    fun getAllProjects(): LiveData<List<DatabaseProject>>

    @Query("SELECT * FROM project_table WHERE id = :id")
    fun getProjectById(id : Int): DatabaseProject?

    @Query("SELECT * FROM project_table WHERE project_customer_id = :id")
    fun getProjectsFromCustomerId(id: Int): List<DatabaseProject>?

}