package com.example.test.screens.project

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.ProjectFragmentItemListBinding
import kotlinx.android.synthetic.main.project_fragment_item_list.view.*

class ProjectListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ProjectFragmentItemListBinding =
            DataBindingUtil.inflate(inflater, R.layout.project_fragment_item_list, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ProjectListViewModelFactory(application);
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ProjectListViewModel::class.java)

        binding.listProjectViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ProjectListAdapter( ProjectListener{
                projectId ->
            findNavController().navigate(
                ProjectListFragmentDirections.actionProjectListFragmentToProjectFragment(projectId))

        })
        val recyclerView = binding.projectNames
        recyclerView.adapter = adapter

        viewModel.listProjects.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        // For searching the list
        binding.searchProjectSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        binding.searchProjectSv.setOnCloseListener {
            viewModel.listProjects.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
            true
        }

        return binding.root
    }

}