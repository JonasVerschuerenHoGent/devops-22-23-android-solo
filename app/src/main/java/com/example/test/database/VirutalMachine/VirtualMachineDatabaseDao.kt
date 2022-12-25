package com.example.test.database.VirutalMachine

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
 *
 * Contains functions to insert and get DatabaseVirtualMachines
 * Note: 2 insert-methods --> one insert only one Actor and the other takes a vararg Actors to insert all Actors
 * Note: getActorsLive --> return a list of Actors as live data
 *
 * IMPORTANT: the DAO only knows about DatabaseVirtualMachines
 *
 */
@Dao
interface VirtualMachineDatabaseDao {

    @Insert
    suspend fun insert(customer : DatabaseVirtualMachine)

    // Adding an insert all with a vararg parameter. Replace strategy is upsert (updating if exists, inserting when not existing, https://betterprogramming.pub/upserting-in-room-8207a100db53)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg actors: DatabaseVirtualMachine)

    @Query("SELECT * FROM virtual_machine_table ORDER BY id ASC")
    suspend fun getAllVirtualMachines(): List<DatabaseVirtualMachine>

    @Query("SELECT * FROM virtual_machine_table ORDER BY id ASC")
    fun getAllVirtualMachinesLive(): LiveData<List<DatabaseVirtualMachine>>

    @Query("SELECT * FROM virtual_machine_table WHERE id = :id")
    suspend fun getVirtualMachineById(id : Int): DatabaseVirtualMachine?

}