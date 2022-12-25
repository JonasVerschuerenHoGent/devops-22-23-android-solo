package com.example.test.database.project

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//@Database(entities = [DatabaseProject::class], version = 1, exportSchema = false)
abstract class ProjectDatabase : RoomDatabase() {/*
    abstract val customerDatabaseDao : ProjectDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, ProjectDatabase::class.java, "customer_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }*/
}