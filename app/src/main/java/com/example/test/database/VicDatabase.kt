package com.example.test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.database.VirutalMachine.DatabaseVirtualMachine
import com.example.test.database.VirutalMachine.VirtualMachineDatabaseDao
import com.example.test.database.customer.CustomerDatabaseDao
import com.example.test.database.customer.DatabaseCustomer
import com.example.test.database.member.DatabaseMember
import com.example.test.database.member.MemberDatabaseDao
import com.example.test.database.project.DatabaseProject
import com.example.test.database.project.ProjectDatabaseDao


@Database(entities = [DatabaseCustomer::class, DatabaseMember::class, DatabaseProject::class, DatabaseVirtualMachine::class], version = 1, exportSchema = true)
abstract class VicDatabase: RoomDatabase() {

    abstract val customerDatabaseDao : CustomerDatabaseDao
    abstract val memberDatabaseDao : MemberDatabaseDao
    abstract val projectDatabaseDao : ProjectDatabaseDao
    abstract val virtualMachineDatabaseDao : VirtualMachineDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: VicDatabase? = null

        fun getInstance(context: Context): VicDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, VicDatabase::class.java, "vic_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}