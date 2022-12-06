package com.example.test.screens.users

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.AccountMock
import com.example.test.R
import com.example.test.databinding.UserDetailFragmentBinding
import com.example.test.domain.Account


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserFragment : Fragment() {

    //binding
    private lateinit var binding: UserDetailFragmentBinding
    private var arraylist: ArrayList<Account> = ArrayList()

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        val args = UserFragmentArgs.fromBundle(requireArguments())

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.user_detail_fragment, container, false)

        //viewModel
        val viewModelFactory = UserViewModelFactory(args.userId)
        val viewModel: UserViewModel by viewModels{viewModelFactory}

        //viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.usersViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)


        //Add items to the ListView and make it clickable
        arraylist = AccountMock().users
        //binding.userNameLbl.text = arraylist[args.userId].name


        return binding.root
    }



}
