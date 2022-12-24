package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineListBinding
import com.example.test.databinding.ListUsersFragmentBinding
import com.example.test.screens.users.ListUsersAdapter
import com.example.test.screens.users.ListUsersFragment
import com.example.test.screens.users.ListUsersViewModel


class VirtualMachineListFragment : Fragment() {
    companion object {
        fun newInstance() = ListUsersFragment()
    }

    private lateinit var binding: FragmentVirtualMachineListBinding
    private lateinit var adapter: VirtualMachinesAdapter
    private lateinit var viewModel: VirtualMachineListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_virtual_machine_list, container, false)

        val viewModelFactory = VirtualMachineListViewModelFactory();
        viewModel = ViewModelProvider(this, viewModelFactory).get(VirtualMachineListViewModel::class.java)

        binding.virtualMachineListViewModel = viewModel
        binding.lifecycleOwner = this

        adapter = VirtualMachinesAdapter( VirtualMachineListener {
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

        viewModel.listVM.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it) })

        return binding.root
    }
}



