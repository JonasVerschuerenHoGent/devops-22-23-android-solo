package com.example.test.screens.project

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.ProjectFragmentItemListBinding
import com.example.test.screens.customers.ListCustomersViewModel
import com.example.test.screens.customers.ListCustomersViewModelFactory

class ProjectListFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var binding: ProjectFragmentItemListBinding
    private lateinit var adapter: ProjectListAdapter
    private val viewModel: ProjectListViewModel by lazy {
        ViewModelProvider(this, ProjectListViewModelFactory()).get(
            ProjectListViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.project_fragment_item_list, container, false)
        binding.listProjectViewModel = viewModel
        binding.lifecycleOwner = this

        adapter = ProjectListAdapter( ProjectListener{

        findNavController().navigate(
                ProjectListFragmentDirections.actionProjectListFragmentToVirtualMachineListFragment())

        })
        val recyclerView = binding.projectNames
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL)
        )

        viewModel.listProjects.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        return binding.root
    }

}