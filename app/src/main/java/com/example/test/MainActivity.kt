package com.example.test

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.SurfaceControl.Transaction
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.test.databinding.ActivityMainBinding
import com.example.test.screens.login.LoginActivity
import com.example.test.screens.project.ProjectListFragment
import com.example.test.screens.users.ListUsersFragment
import com.example.test.screens.users.UserFragment
import com.example.test.screens.virtual_machines.VirtualMachineListFragment
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
        val navController = this.findNavController(R.id.nav_host_fragment)

        //code for overflow menu
        addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.aboutFragment) {
                    return NavigationUI.onNavDestinationSelected(menuItem, navController)
                }
                return false
            }

        })

        //setup drawermenu
        setSupportActionBar(toolbar)



        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navView, navController)

        //setup bottom nav bar
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            lateinit var fragment: Fragment
            when(it.itemId) {
                R.id.projects -> fragment = ProjectListFragment()
                R.id.virtualMachines -> fragment = VirtualMachineListFragment()
                R.id.users -> fragment = ListUsersFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.drawerLayout, fragment).commit()
            true
        }

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
