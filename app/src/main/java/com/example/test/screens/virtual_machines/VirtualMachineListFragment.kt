package com.example.test.screens.virtual_machines

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import com.example.test.databinding.FragmentVirtualMachineListBinding

class VirtualMachineListFragment : Fragment() {
    private lateinit var binding: FragmentVirtualMachineListBinding

    private lateinit var listView: ListView
    private var arrayList: ArrayList<VirtualMachine> = ArrayList()
    var adapter: VirtualMachinesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_virtual_machine_list, container, false)

        listView = binding.virtualMachineList
        arrayList = VirtualMachineMock().virtualMachines
        adapter = this.context?.let { VirtualMachinesAdapter(it, arrayList) }
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this.context, "Clicked item : $position",Toast.LENGTH_SHORT).show()

            findNavController()
                .navigate(VirtualMachineListFragmentDirections
                .actionVirtualMachineListFragmentToVirtualMachineDetailFragment(position))
        }
        return binding.root
    }

    class VirtualMachinesAdapter(private val context: Context, private val arrayList: java.util.ArrayList<VirtualMachine>) : BaseAdapter() {
        private lateinit var state: TextView
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
            convertView = LayoutInflater.from(context).inflate(R.layout.vm_item, parent, false)
            name = convertView?.findViewById(R.id.vm_name_textview)!!
            name.text = arrayList[position].name
            state = convertView?.findViewById(R.id.vm_state_textview)!!
            state.text = arrayList[position].state.toString()
            return convertView
        }
    }
}