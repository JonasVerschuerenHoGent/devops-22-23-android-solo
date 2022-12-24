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
import com.example.test.databinding.ListUsersFragmentBinding
import com.example.test.databinding.ProjectFragmentItemListBinding
import com.example.test.screens.users.ListUsersAdapter
import com.example.test.screens.users.ListUsersViewModel

class ProjectListFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private lateinit var binding: ProjectFragmentItemListBinding
    private lateinit var adapter: ProjectListAdapter
    private lateinit var viewModel: ProjectListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = DataBindingUtil.inflate(inflater, R.layout.project_fragment_item_list, container, false)

        val viewModelFactory = ProjectListViewModelFactory();
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectListViewModel::class.java)

        binding.listProjectViewModel = viewModel
        binding.lifecycleOwner = this

        adapter = ProjectListAdapter( ProjectListener{
                projectName ->
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