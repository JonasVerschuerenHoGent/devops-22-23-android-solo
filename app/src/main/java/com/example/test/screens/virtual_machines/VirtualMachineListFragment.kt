package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineListBinding
import com.example.test.interfaces.VirtualMachineApi
import com.example.test.utils.RetrofitBuilder

class VirtualMachineListFragment : Fragment() {

    private lateinit var virtualMachineApi : VirtualMachineApi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        virtualMachineApi = RetrofitBuilder.getInstance().create(VirtualMachineApi::class.java)
        val binding: FragmentVirtualMachineListBinding=
            DataBindingUtil.inflate(inflater, R.layout.fragment_virtual_machine_list, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = VirtualMachineListViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[VirtualMachineListViewModel::class.java]

        binding.virtualMachineListViewModel = viewModel
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

        viewModel.listVms.observe(viewLifecycleOwner) {
            println("List got ${it.size}")
            adapter.submitList(it)
        }

        return binding.root
    }
}



