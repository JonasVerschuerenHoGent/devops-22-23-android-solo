package com.example.test.screens.users

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.*
import com.example.test.databinding.ListUsersFragmentBinding
import com.example.test.domain.Account
import com.example.test.interfaces.AccountApi
import com.example.test.utils.RetrofitBuilder


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListUsersFragment : Fragment() {

    //binding
    private lateinit var binding: ListUsersFragmentBinding
    //viewModel
    private lateinit var viewModel: ListUsersViewModel

//    private var arraylist: ArrayList<Account> = ArrayList()
    private lateinit var adapter: ListUsersAdapter

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.list_users_fragment, container, false)

        //code for overflow menu
      /*  val menuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.listUsersFragment){
                    return NavigationUI.onNavDestinationSelected(
                        menuItem,
                        requireView().findNavController()
                    )
                }
                if(menuItem.itemId == R.id.virtualMachineListFragment){
                    return NavigationUI.onNavDestinationSelected(
                        menuItem,
                        requireView().findNavController()
                    )
                }
                if(menuItem.itemId == R.id.projectListFragment){
                    return NavigationUI.onNavDestinationSelected(
                        menuItem,
                        requireView().findNavController()
                    )
                }
                return false
            }
        }, viewLifecycleOwner) */

        val viewModelFactory = ListUsersViewModelFactory();
        //Get the viewModel
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListUsersViewModel::class.java)

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.listUsersViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this
       // arraylist = AccountMock().users
        adapter = ListUsersAdapter(AccountClickListener { accountID: Int -> Toast.makeText(context, accountID, Toast.LENGTH_SHORT).show() })
        binding.userList.adapter = adapter
        //Add items to the ListView and make it clickable
     /*   val arrayAdapter: ArrayAdapter<Account> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arraylist)
        binding.usersLv.adapter = arrayAdapter
        binding.usersLv.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                findNavController()
                    .navigate(ListUsersFragmentDirections
                        .actionListUsersFragmentToUserFragment(position))
                Toast.makeText(requireContext(), "Clicked item = " + arraylist.get(position), Toast.LENGTH_LONG).show()
            }

        } */
        viewModel.listUsers.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })


        return binding.root
    }

//    class UserAdapter(private val context: Context, private val arrayList: java.util.ArrayList<Account>) : BaseAdapter() {
//        private lateinit var state: TextView
//        private lateinit var name: TextView
//        override fun getCount(): Int {
//            return arrayList.size
//        }
//        override fun getItem(position: Int): Any {
//            return position
//        }
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
//            var convertView = convertView
//            convertView = LayoutInflater.from(context).inflate(R.layout.vm_item, parent, false)
//            name = convertView?.findViewById(R.id.vm_name_textview)!!
//            name.text = arrayList[position].name
//            state = convertView?.findViewById(R.id.vm_state_textview)!!
//            state.text = arrayList[position].state.toString()
//            return convertView
//        }
//    }




}
