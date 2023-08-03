package com.example.test.database.VirutalMachine

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.database.customer.DatabaseCustomer


@Dao
interface VirtualMachineDatabaseDao {

    @Insert
    fun insert(customer : DatabaseVirtualMachine)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg vms: DatabaseVirtualMachine)

    @Query("SELECT * FROM virtualmachine_table ORDER BY id ASC")
    fun getAllVirtualMachines(): LiveData<List<DatabaseVirtualMachine>>

    @Query("SELECT * FROM virtualmachine_table WHERE id = :id ORDER BY id ASC")
    fun getVirtualMachineById(id: Int): LiveData<DatabaseVirtualMachine>


    @Query("SELECT * FROM virtualmachine_table WHERE vm_project_id = :id")
    fun getVmsFromProjectId(id: Int): LiveData<List<DatabaseVirtualMachine>>?

    @Query("SELECT * FROM virtualmachine_table WHERE vm_customer_id = :id")
    fun getVmsFromCustomerId(id: Int): LiveData<List<DatabaseVirtualMachine>>?

    @Query("SELECT * FROM virtualmachine_table WHERE vm_creator_id = :id")
    fun getVmsFromMemberId(id: Int): LiveData<List<DatabaseVirtualMachine>>?



}