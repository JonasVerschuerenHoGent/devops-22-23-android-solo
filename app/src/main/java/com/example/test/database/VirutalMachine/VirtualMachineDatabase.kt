package com.example.test.database.VirutalMachine

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [DatabaseVirtualMachine::class], version = 1, exportSchema = false)
abstract class VirtualMachineDatabase : RoomDatabase() {
    abstract val virtualMachineDatabaseDao : VirtualMachineDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: VirtualMachineDatabase? = null

        fun getInstance(context: Context): VirtualMachineDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, VirtualMachineDatabase::class.java, "customer_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}