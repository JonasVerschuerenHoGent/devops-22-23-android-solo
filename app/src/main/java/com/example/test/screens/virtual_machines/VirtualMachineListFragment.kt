package com.example.test.screens.virtual_machines

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import com.example.test.databinding.FragmentVirtualMachineListBinding
import com.example.test.interfaces.VirtualMachineApi
import com.example.test.utils.RetrofitBuilder
import java.time.LocalDate
import java.util.Calendar

class VirtualMachineListFragment : Fragment() {
    private lateinit var binding: FragmentVirtualMachineListBinding

    private lateinit var listView: ListView
    private lateinit var virtualMachineApi : VirtualMachineApi
    private var arrayList: ArrayList<VirtualMachine> = ArrayList()
    var adapter: VirtualMachinesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        virtualMachineApi = RetrofitBuilder.getInstance().create(VirtualMachineApi::class.java)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_virtual_machine_list, container, false)

        listView = binding.virtualMachineList
        arrayList = VirtualMachineMock().virtualMachines
        adapter = this.context?.let { VirtualMachinesAdapter(it, arrayList) }
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this.context, "Clicked item : $position", Toast.LENGTH_SHORT).show()
            findNavController()
                .navigate(VirtualMachineListFragmentDirections
                    .actionVirtualMachineListFragmentToVirtualMachineDetailFragment(position))
        }
        return binding.root
    }
}

class VirtualMachinesAdapter(
    private val context: Context,
    private val arrayList: java.util.ArrayList<VirtualMachine>,
) : BaseAdapter() {
    private lateinit var active: TextView
    private lateinit var name: TextView
    private lateinit var vcpu: TextView
    private lateinit var memory: TextView
    private lateinit var storage: TextView

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

        //name
        name = convertView?.findViewById(R.id.vm_name_textview)!!
        name.text = arrayList[position].name

        //state
        active = convertView?.findViewById(R.id.vm_active_textview)!!

        if(arrayList[position].beginDate <= LocalDate.now() && //startDate is in past
            arrayList[position].endDate > LocalDate.now() && //endDate is in future
            arrayList[position].beginDate < arrayList[position].endDate//startdate is before enddate
        ){
            active.text =  "actief"
        }
        else{
            active.text =  "inactief"
        }

        //vcpu
        vcpu = convertView?.findViewById(R.id.vm_vcpu_textview)!!
        vcpu.text = "#vCPU: " + arrayList[position].vCPUAmount.toString()

        //memory
        memory = convertView?.findViewById(R.id.vm_memory_textview)!!
        memory.text = "Geheugen(GB): " + arrayList[position].memoryAmount.toString()

        //storage
        storage = convertView?.findViewById(R.id.vm_storage_textview)!!
        storage.text = "Opslag(GB): " + arrayList[position].storageAmount.toString() //"debug: " + arrayList[position].endDate.toString()

        return convertView
    }
}

