package com.example.test.screens.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.ListMembersFragmentBinding

class ListMembersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ListMembersFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_members_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ListMembersViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ListMembersViewModel::class.java]

        binding.listMembersViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ListMembersAdapter( MemberListener{
                memberID ->
            findNavController().navigate(
                ListMembersFragmentDirections.actionListMembersFragmentToMemberFragment(
                memberID
            ))
        })
        val recyclerView = binding.memberList
        recyclerView.adapter = adapter

        viewModel.listMembers.observe(viewLifecycleOwner) { adapter.submitList(it) }

        // Setting the listeners for the chips
        binding.chipAdmin.setOnClickListener {
            viewModel.onFilterChanged(it.tag.toString(), binding.chipAdmin.isChecked)
        }
        binding.chipManager.setOnClickListener {
            viewModel.onFilterChanged(it.tag.toString(), binding.chipManager.isChecked)
        }
        binding.chipViewer.setOnClickListener {
            viewModel.onFilterChanged(it.tag.toString(), binding.chipViewer.isChecked)
        }


        return binding.root
    }
}