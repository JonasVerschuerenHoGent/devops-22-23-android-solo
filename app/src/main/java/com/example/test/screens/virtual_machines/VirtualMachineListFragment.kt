package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineListBinding

class VirtualMachineListFragment : Fragment() {
    companion object {
        fun newInstance() = VirtualMachineListFragment()
    }

    private lateinit var binding: FragmentVirtualMachineListBinding
    private lateinit var adapter: VirtualMachinesAdapter
    private val viewModel: VirtualMachineListViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, VirtualMachineListViewModelFactory(activity.application)).get(
            VirtualMachineListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_virtual_machine_list, container, false)
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
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )

        viewModel.virtualMachineList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it) })

        return binding.root
    }
}



