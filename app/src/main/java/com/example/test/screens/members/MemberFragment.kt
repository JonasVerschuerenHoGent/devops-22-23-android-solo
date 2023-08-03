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

    private lateinit var binding: MemberDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val args = MemberFragmentArgs.fromBundle(requireArguments())

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
        binding.membersViewModel = viewModel


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


        viewModel.listVms.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it) })

        return binding.root
    }
}