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

class ProjectListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProjectFragmentItemListBinding =
            DataBindingUtil.inflate(inflater, R.layout.project_fragment_item_list, container, false)

        val viewModelFactory = ProjectListViewModelFactory();
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectListViewModel::class.java)

        binding.listProjectViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ProjectListAdapter( ProjectListener{
                projectName ->
            findNavController().navigate(
                ProjectListFragmentDirections.actionProjectListFragmentToVirtualMachineListFragment())

        })
        val recyclerView = binding.projectNames
        recyclerView.adapter = adapter

        viewModel.listProjects.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        return binding.root
    }

}