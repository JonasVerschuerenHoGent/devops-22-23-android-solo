package com.example.test.screens.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.MemberDetailFragmentBinding
import com.example.test.screens.virtual_machines.*

class MemberFragment: Fragment() {
    //binding
    private lateinit var binding: MemberDetailFragmentBinding
    private val viewModel: MemberViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, MemberListViewModelFactory(activity.application)).get(
           MemberViewModel::class.java)
    }

    private val vmViewModel: VirtualMachineListViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, VirtualMachineListViewModelFactory(activity.application)).get(
            VirtualMachineListViewModel::class.java)
    }



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

             // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.membersViewModel = viewModel

        //Add items to the ListView and make it clickable
        //binding.userNameLbl.text = arraylist[args.userId].name

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

        vmViewModel.virtualMachineList.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it) })

        return binding.root
    }
}