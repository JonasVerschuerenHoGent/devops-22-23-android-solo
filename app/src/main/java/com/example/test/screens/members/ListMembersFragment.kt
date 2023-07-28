package com.example.test.screens.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.ListMembersFragmentBinding
import com.google.android.material.chip.Chip

class ListMembersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ListMembersFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_members_fragment, container, false)

        val viewModelFactory = ListMembersViewModelFactory()
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

//        viewModel.listMembers.observe(viewLifecycleOwner, object: Observer<List<String>> {
//            override fun onChanged(data: List<String>?) {
//                data ?: return
//
//                //Code for chips
//                val chipGroup = binding.rolesList
//                val inflator =  LayoutInflater.from(chipGroup.context)
//                val children: List<Chip> = data.map { role ->
//                    val chip = inflator.inflate(R.layout.roles, chipGroup, false) as Chip
//                    chip.text = role
//                    chip.tag = role
//                    chip.setOnCheckedChangeListener{ button, isChecked ->
//                        viewModel.onFilterChanged(button.tag as String, isChecked)
//                    }
//                    chip
//                }
//
//                chipGroup.removeAllViews()
//                for (chip in children) {
//                    chipGroup.addView(chip)
//                }
//
//            }
//        })


        return binding.root
    }
}