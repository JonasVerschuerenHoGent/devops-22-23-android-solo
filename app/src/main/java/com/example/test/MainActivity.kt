package com.example.test

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.test.databinding.ActivityMainBinding
import com.example.test.screens.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var prefs : SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO check when login is implement and only do this if an invalid token is provided
        prefs = this.getSharedPreferences("com.example.test", Context.MODE_PRIVATE)
        if(prefs?.getBoolean("hasLoggedIn", false) == false) {
            startActivity(Intent(this, LoginActivity::class.java))

        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //setup overflow- and drawermenu
        setSupportActionBar(toolbar)

        val navController = this.findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)

        //setup bottom nav bar
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.projects -> {
                    Log.d("mainActivity", "projectlistfragment is clicked")
                    NavigationUI.onNavDestinationSelected(it, navController)
                }
                R.id.virtualMachines -> {
                    NavigationUI.onNavDestinationSelected(it, navController)
                }
                R.id.users -> {
                    NavigationUI.onNavDestinationSelected(it, navController)
                }
                else -> false
            }
        }

    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.drawerLayout, fragment).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


    override fun onStop() {
        super.onStop()
        prefs?.edit()?.putBoolean("hasLoggedIn", false)?.apply()
    }
}
