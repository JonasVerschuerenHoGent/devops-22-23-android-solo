package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineDetailBinding
import com.example.test.domain.VirtualMachine
import com.example.test.domain.VirtualMachineMock
import com.example.test.interfaces.VirtualMachineApi
import com.example.test.utils.RetrofitBuilder
import java.time.LocalDate
import java.util.*

class VirtualMachineDetailFragment : Fragment() {

    private lateinit var binding: FragmentVirtualMachineDetailBinding
    private lateinit var virtualMachineApi : VirtualMachineApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        virtualMachineApi = RetrofitBuilder.getInstance().create(VirtualMachineApi::class.java)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_virtual_machine_detail,
            container,
            false
        )

        val args = VirtualMachineDetailFragmentArgs.fromBundle(requireArguments())
        val myVM = VirtualMachineMock().virtualMachines[args.vmId]
        binding.myVM = myVM

        binding.textviewName.text = "${myVM.name}"
        binding.textviewHostname.text = "${myVM.hostname}"
        binding.textviewFqdn.text = "${myVM.fqdn}"
        binding.textviewMode.text = "${myVM.mode.printableName}"
        //binding.textviewTemplate.text = "${myVM.mode.printableName}"

        var backupText = ""
        backupText += when(myVM.backupFrequency){
            0 -> "none"
            1 -> "daily"
            7 -> "weekly"
            14 -> "bi-weekly"
            30 -> "monthly"
            else -> "every ${myVM.backupFrequency} days"
        }
        binding.textviewBackup.text = backupText

        binding.textviewAvailability.text = "${myVM.availability.printableName}"
        binding.textviewHost.text = "${myVM.hostServer}"
        binding.textviewCluster.text = "${myVM.cluster}"

        var ports = ""
        for(p in myVM.ports){
            ports += "${p}; "
        }
        binding.textviewPorts.text = ports

        if(myVM.beginDate <= LocalDate.now() && //startDate is in past
            myVM.endDate > LocalDate.now()&& //endDate is in future
            myVM.beginDate < myVM.endDate//startdate is before enddate
        ){
            binding.textviewState.text =  "actief"
        }
        else{
            binding.textviewState.text = "inactief"
        }

        //binding.textviewState.text = "${myVM.state.printableName}"
        binding.textviewVcpu.text = "${myVM.vCPUAmount}"
        binding.textviewMemory.text = "${myVM.memoryAmount}"
        binding.textviewStorage.text = "${myVM.storageAmount}"

        binding.textviewBegindate.text = "${myVM.beginDate.toString()}"
        binding.textviewEnddate.text = "${myVM.endDate}"
        binding.textviewRequestdate.text = "${myVM.requestDate}"

        return binding.root
    }
}