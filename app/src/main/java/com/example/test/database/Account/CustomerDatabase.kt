package com.example.test.database.Account

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


/**
 * Customer database
 *
 * @constructor Create empty Customer room database
 */
@Database(entities = [DatabaseCustomer::class], version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {
    abstract val customerDatabaseDao : CustomerDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getInstance(context: Context): CustomerDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, CustomerDatabase::class.java, "customer_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}