package com.example.test.screens.Project

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import com.example.test.databinding.ProjectFragmentItemListBinding
import com.example.test.domain.Project
import com.example.test.domain.ProjectMock
import kotlinx.android.synthetic.main.project_fragment_item_list.*


/**
 * A fragment representing a list of Items.
 */
class ProjectListFragment : Fragment() {

    private lateinit var binding: ProjectFragmentItemListBinding
    private lateinit var listView: ListView
    private var arrayList: ArrayList<Project> = ArrayList()
    private var adapter : ProjectsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.project_fragment_item_list, container, false)

        listView = binding.projectNames
        arrayList = ProjectMock().projects

        //code for overflow menu
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.listUsersFragment) {
                    return NavigationUI.onNavDestinationSelected(
                        menuItem,
                        requireView().findNavController()
                    )
                }
                if (menuItem.itemId == R.id.virtualMachineListFragment) {
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
        }, viewLifecycleOwner)

        adapter = this.context?.let {ProjectsAdapter(it,arrayList) }
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this.context, "Clicked item : $position", Toast.LENGTH_SHORT).show()

            findNavController()
                .navigate(ProjectListFragmentDirections.actionProjectListFragmentToVirtualMachineListFragment())

        }
        return binding.root
    }

    class ProjectsAdapter(private val context: Context, private val arrayList: java.util.ArrayList<Project>) : BaseAdapter() {
        private lateinit var name: TextView
        override fun getCount(): Int {
            return arrayList.size
        }
        override fun getItem(position: Int): Any {
            return position
        }
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
            var convertView = convertView
            convertView = LayoutInflater.from(context).inflate(R.layout.project_fragment_item, parent, false)
            name = convertView?.findViewById(R.id.projectName)!!
            name.text = arrayList[position].name
            return convertView
        }
    }

}