package com.example.test.screens.customers

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
import com.example.test.databinding.ListCustomersFragmentBinding

class ListCustomersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: ListCustomersFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_customers_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val viewModelFactory = ListCustomersViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ListCustomersViewModel::class.java]

        binding.listCustomersViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ListCustomersAdapter( CustomerListener{
                customerId ->
            findNavController().navigate(ListCustomersFragmentDirections.actionListCustomersFragmentToCustomerFragment(
                customerId
            ))
        })

        val recyclerView = binding.customerList
        recyclerView.adapter = adapter

        viewModel.listCustomers.observe(viewLifecycleOwner) { adapter.submitList(it) }

        return binding.root
    }
}
