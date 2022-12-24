package com.example.test

import android.app.Application
import timber.log.Timber

class VicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}