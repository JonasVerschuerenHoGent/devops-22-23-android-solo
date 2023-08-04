package com.example.test.screens.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.ProjectDetailFragmentBinding
import com.example.test.screens.virtual_machines.VirtualMachineListFragmentDirections
import com.example.test.screens.virtual_machines.VirtualMachineListener
import com.example.test.screens.virtual_machines.VirtualMachinesAdapter

class ProjectFragment: Fragment() {

    private lateinit var binding: ProjectDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val args = ProjectFragmentArgs.fromBundle(requireArguments())

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.project_detail_fragment,
            container,
            false
        )

        //viewmodel
        val application = requireNotNull(this.activity).application
//        Log.i("ProjectFragment", "PRINTING PROJECT ID")
//        Log.i("ProjectFragment", args.projectId.toString())
        val viewModelFactory = ProjectViewModelFactory(application, args.projectId)
        val viewModel: ProjectViewModel by viewModels { viewModelFactory }
        binding.projectViewModel = viewModel

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


        viewModel.listVms?.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it) })

        return binding.root
    }


}