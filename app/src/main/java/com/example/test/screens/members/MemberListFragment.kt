package com.example.test.screens.members

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.R
import com.example.test.databinding.ListMembersFragmentBinding

class MemberListFragment() : Fragment() {


    private lateinit var binding: ListMembersFragmentBinding
    private lateinit var adapter: MemberListAdapter

    private val viewModel: MemberListViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, MemberListViewModelFactory(activity.application)).get(MemberListViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.list_members_fragment, container, false)

        binding.listMembersViewModel = viewModel
        binding.lifecycleOwner = this

        adapter = MemberListAdapter( MemberListener{
                memberID ->
            findNavController().navigate(
                MemberListFragmentDirections.actionListMembersFragmentToMemberFragment(
                memberID
            ))
        })
        val recyclerView = binding.memberList
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        )

        viewModel.listMembers.observe(viewLifecycleOwner) { adapter.submitList(it) }



        return binding.root
    }
}