package com.example.test.database.VirutalMachine

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface VirtualMachineDatabaseDao {

    @Insert
    fun insert(customer : DatabaseVirtualMachine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg vms: DatabaseVirtualMachine)

    @Query("SELECT * FROM virtualmachine_table ORDER BY id DESC")
    fun getAllVirtualMachines(): LiveData<List<DatabaseVirtualMachine>>

    @Query("SELECT * FROM virtualmachine_table WHERE id = :id")
    fun getVirtualMachineById(id : Int): DatabaseVirtualMachine?



    @Query("SELECT * FROM virtualmachine_table WHERE vm_project_id = :id")
    fun getVmsFromProjectId(id: Int): List<DatabaseVirtualMachine>?

}