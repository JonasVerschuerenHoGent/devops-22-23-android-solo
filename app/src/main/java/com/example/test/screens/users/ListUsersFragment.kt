package com.example.test.screens.users

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.*
import com.example.test.databinding.ListUsersFragmentBinding

class ListUsersFragment : Fragment() {


    private lateinit var binding: ListUsersFragmentBinding
    private lateinit var adapter: ListUsersAdapter

    private val viewModel: ListUsersViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ListUsersViewModelFactory()).get(ListUsersViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.list_users_fragment, container, false)

        // Data binding of ViewModel and LifeCycleOwner
        binding.listUsersViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.listUsersViewModel = viewModel
        binding.lifecycleOwner = this

        adapter = ListUsersAdapter( AccountListener{
            accountID ->
            findNavController().navigate(ListUsersFragmentDirections.actionListUsersFragmentToUserFragment(
                accountID
            ))

        })
        val recyclerView = binding.userList
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL))

        viewModel.customers.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        return binding.root
    }
}
