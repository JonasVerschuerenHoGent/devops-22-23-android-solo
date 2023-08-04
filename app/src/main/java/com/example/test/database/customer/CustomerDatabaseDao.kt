package com.example.test.database.customer

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CustomerDatabaseDao {

    @Insert
    fun insert(customer : DatabaseCustomer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg customers: DatabaseCustomer)

    @Query("SELECT * FROM customer_table ORDER BY id ASC")
    fun getAllCustomers(): LiveData<List<DatabaseCustomer>>

    @Query("SELECT * FROM customer_table WHERE id = :id ORDER BY id ASC")
    fun getCustomerById(id: Int): LiveData<DatabaseCustomer>

}