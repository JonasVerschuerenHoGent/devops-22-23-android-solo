package com.example.test.screens.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.MemberDetailFragmentBinding
import com.example.test.domain.Member
import com.example.test.domain.MemberMock
import com.example.test.screens.virtual_machines.*

class MemberFragment: Fragment() {
    //binding
    private lateinit var binding: MemberDetailFragmentBinding
    private var arraylist: ArrayList<Member> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val args = MemberFragmentArgs.fromBundle(requireArguments())

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.member_detail_fragment,
            container,
            false
        )

        //viewModel
        val application = requireNotNull(this.activity).application
        val viewModelFactory = MemberViewModelFactory(application, args.memberId)
        val viewModel: MemberViewModel by viewModels{viewModelFactory}

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.membersViewModel = viewModel

        //Add items to the ListView and make it clickable
        arraylist = MemberMock().members
        //binding.userNameLbl.text = arraylist[args.userId].name

        val vmViewModelFactory = VirtualMachineListViewModelFactory(application);
        val vmViewModel = ViewModelProvider(this, vmViewModelFactory).get(
            VirtualMachineListViewModel::class.java)
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

        vmViewModel.listVms.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it) })

        return binding.root
    }
}