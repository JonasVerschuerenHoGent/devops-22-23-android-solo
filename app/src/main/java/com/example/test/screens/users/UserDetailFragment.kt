package com.example.test.screens.users

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.test.AccountMock
import com.example.test.R
import com.example.test.databinding.UserDetailFragmentBinding

class UserDetailFragment : Fragment() {

    //binding
    private lateinit var binding: UserDetailFragmentBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter : ListUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val args = UserDetailFragmentArgs.fromBundle(requireArguments())

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_detail_fragment,
            container,
            false
        )

        //viewModel
        val viewModelFactory = UserViewModelFactory(args.userId)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.usersViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this


        //Add items to the ListView and make it clickable
        viewModel.customer.observe(viewLifecycleOwner, Observer { it }) // TODO fill up detail screen. &  fill up detailcustomer

        return binding.root
    }



}
