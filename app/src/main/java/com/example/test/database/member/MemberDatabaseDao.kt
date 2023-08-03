package com.example.test.database.member

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test.database.customer.DatabaseCustomer


@Dao
interface MemberDatabaseDao {

    @Insert
    fun insert(member : DatabaseMember)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg members: DatabaseMember)

    @Query("SELECT * FROM member_table ORDER BY id ASC")
    fun getAllMembers(): LiveData<List<DatabaseMember>>

    @Query("SELECT * FROM member_table WHERE id = :id ORDER BY id ASC")
    fun getMemberById(id: Int): LiveData<DatabaseMember>

}