package com.example.test.screens.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {


    private var cachedCredentials: Credentials? = null
    private var cachedUserProfile: UserProfile? = null
    private lateinit var binding: FragmentLoginBinding
    private var prefs: SharedPreferences? = null
    private lateinit var account: Auth0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        prefs = activity?.getSharedPreferences("com.example.test", Context.MODE_PRIVATE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            //TODO Log user in and check credentials
//            prefs?.edit()?.putBoolean("hasLoggedIn", true)?.apply()
//            startActivity(Intent(activity, MainActivity::class.java))

        }
    }


}
