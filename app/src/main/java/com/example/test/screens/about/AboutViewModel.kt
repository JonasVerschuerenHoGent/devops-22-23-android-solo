package com.example.test.screens.about

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel: ViewModel() {

    private val _clickedFab = MutableLiveData<Boolean>()
    val clickedFab: LiveData<Boolean>
        get() = _clickedFab

    fun onFabClicked() {
        Log.i("About", "You have clicked on the FAB")
        _clickedFab.value = true
    }
    fun onFabAction() {
        _clickedFab.value = false
    }

}