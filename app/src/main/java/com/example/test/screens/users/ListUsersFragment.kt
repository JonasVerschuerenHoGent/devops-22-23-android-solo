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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ListUsersFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_users_fragment, container, false)

        val viewModelFactory = ListUsersViewModelFactory();
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ListUsersViewModel::class.java)

        binding.listUsersViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ListUsersAdapter( AccountListener{
            accountID ->
            findNavController().navigate(ListUsersFragmentDirections.actionListUsersFragmentToUserFragment(
                accountID
            ))
            //Toast.makeText(context, "$accountID", Toast.LENGTH_SHORT).show()
            //Log.d("onclick", "accountclicklistener executed")
        })
        val recyclerView = binding.userList
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL))

        viewModel.listUsers.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        return binding.root
    }
}
