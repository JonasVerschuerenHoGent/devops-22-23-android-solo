package com.example.test.screens.users

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.AccountMock
import com.example.test.R
import com.example.test.databinding.UserDetailFragmentBinding
import com.example.test.domain.Account
import com.example.test.screens.virtual_machines.*

class UserFragment : Fragment() {

    //binding
    private lateinit var binding: UserDetailFragmentBinding
    private var arraylist: ArrayList<Account> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val args = UserFragmentArgs.fromBundle(requireArguments())

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_detail_fragment,
            container,
            false
        )

        //viewModel
        val viewModelFactory = UserViewModelFactory(args.userId)
        val viewModel: UserViewModel by viewModels{viewModelFactory}

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.usersViewModel = viewModel

        //Add items to the ListView and make it clickable
        arraylist = AccountMock().users
        //binding.userNameLbl.text = arraylist[args.userId].name

        val vmViewModelFactory = VirtualMachineListViewModelFactory();
        val vmViewModel = ViewModelProvider(this, vmViewModelFactory).get(VirtualMachineListViewModel::class.java)
        binding.virtualMachineListViewModel = vmViewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        val adapter = VirtualMachinesAdapter( VirtualMachineListener {
                virtualMachineID ->
            findNavController().navigate(VirtualMachineListFragmentDirections.
            actionVirtualMachineListFragmentToVirtualMachineDetailFragment(
                virtualMachineID
            ))
        })

        val recyclerView = binding.virtualMachineList
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )

        vmViewModel.listVms.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it) })


        return binding.root
    }



}
