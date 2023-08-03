package com.example.test.screens.customers

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.domain.CustomerMock
import com.example.test.R
import com.example.test.databinding.CustomerDetailFragmentBinding
import com.example.test.domain.Customer
import com.example.test.screens.virtual_machines.*

class CustomerFragment : Fragment() {

    //binding
    private lateinit var binding: CustomerDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val args = CustomerFragmentArgs.fromBundle(requireArguments())

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.customer_detail_fragment,
            container,
            false
        )

        //viewModels
        val application = requireNotNull(this.activity).application

        val viewModelFactory = CustomerViewModelFactory(application, args.customerId)
        val viewModel: CustomerViewModel by viewModels{viewModelFactory}
        binding.customersViewModel = viewModel

        //TODO: URGENT Change so that the vms of the customer are displayed
//        val vmViewModelFactory = VirtualMachineListViewModelFactory(application)
//        val vmViewModel = ViewModelProvider(this, vmViewModelFactory).get(VirtualMachineListViewModel::class.java)
//        binding.virtualMachineListViewModel = vmViewModel


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

        //TODO: URGENT Change so that the vms of the customer are displayed
        viewModel.listVms.observe(viewLifecycleOwner, Observer {
            println("List got ${it.size}")
            adapter.submitList(it)
        })

        return binding.root
    }



}
