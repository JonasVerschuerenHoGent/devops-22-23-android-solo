package com.example.test.screens.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.test.R
import com.example.test.databinding.ListUsersFragmentBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListUsersFragment : Fragment() {

    //binding
    private lateinit var binding: ListUsersFragmentBinding

    //viewModel
    private lateinit var viewModel: ListUsersViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.list_users_fragment, container, false)

        //Get the viewModel
        viewModel = ViewModelProvider(this).get(ListUsersViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.listUsersViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.setLifecycleOwner(this)


        //Add items to the ListView and make it clickable
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.listUsers.value!!)
        binding.usersLv.adapter = arrayAdapter
        binding.usersLv.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(requireContext(), "Clicked item = " + viewModel.listUsers.value!!.get(position), Toast.LENGTH_LONG).show()
            }

        }


        return binding.root
    }



}
