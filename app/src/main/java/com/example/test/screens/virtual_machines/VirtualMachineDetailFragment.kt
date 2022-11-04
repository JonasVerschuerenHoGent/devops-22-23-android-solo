package com.example.test.screens.virtual_machines

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineDetailBinding
import com.example.test.domain.VirtualMachineMock

class VirtualMachineDetailFragment : Fragment() {

    private lateinit var binding: FragmentVirtualMachineDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        binding.textviewName.text = "Name: ${myVM.name}"
        binding.textviewHostname.text = "Hostname: ${myVM.hostname}"
        binding.textviewFqdn.text = "FQDN: ${myVM.fqdn}"
        binding.textviewMode.text = "Mode: ${myVM.mode.printableName}"
        binding.textviewTemplate.text = "Template: ${myVM.template.printableName}"

        var backupText = "Backup: "
        backupText += when(myVM.backup){
            0 -> "none"
            1 -> "daily"
            7 -> "weekly"
            14 -> "bi-weekly"
            30 -> "monthly"
            else -> "every ${myVM.backup} days"
        }
        binding.textviewBackup.text = backupText

        binding.textviewAvailability.text = "Availability: ${myVM.availability.printableName}"
        binding.textviewHost.text = "Host: ${myVM.host}"
        binding.textviewCluster.text = "Cluster: ${myVM.cluster}"

        var ports = "Ports: "
        for(p in myVM.ports){
            ports += "${p}; "
        }
        binding.textviewPorts.text = ports

        binding.textviewState.text = "State: ${myVM.state.printableName}"
        binding.textviewVcpu.text = "#vCPU: ${myVM.vCPUAmount}"
        binding.textviewMemory.text = "Memory (GB): ${myVM.memoryAmount}"
        binding.textviewStorage.text = "Storage (GB): ${myVM.storageAmount}"


        return binding.root
    }
}