package com.example.test.screens.customers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.test.*
import com.example.test.databinding.ListCustomersFragmentBinding

class ListCustomersFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ListCustomersFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.list_customers_fragment, container, false)

        val viewModelFactory = ListCustomersViewModelFactory()
        val viewModel = ViewModelProvider(this, viewModelFactory)[ListCustomersViewModel::class.java]

        binding.listCustomersViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = ListCustomersAdapter( CustomerListener{
                customerId ->
            findNavController().navigate(ListCustomersFragmentDirections.actionListCustomersFragmentToCustomerFragment(
                customerId
            ))
            //Toast.makeText(context, "$customerId", Toast.LENGTH_SHORT).show()
            //Log.d("onclick", "customerclicklistener executed")
        })
        val recyclerView = binding.customerList
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        viewModel.listCustomers.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })

        return binding.root
    }
}
