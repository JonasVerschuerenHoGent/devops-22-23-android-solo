package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineListBinding


class VirtualMachineListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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


        // For searching the list
        binding.searchVmSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val list = adapter.currentList.filter {
                    it.name.lowercase().contains(query.toString().lowercase())
                }
                adapter.submitList(list)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                val list = adapter.currentList.filter {
                    it.name.lowercase().contains(newText.toString().lowercase())
                }
                adapter.submitList(list)
                return true
            }
        })

        binding.searchVmSv.setOnCloseListener {
            viewModel.listVms.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
            true
        }

        return binding.root
    }
}



