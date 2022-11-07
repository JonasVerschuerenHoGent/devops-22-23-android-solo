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
        
        //code for overflow menu
        val menuHost = requireActivity()
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
                return false
            }
        }, viewLifecycleOwner)

        val args = VirtualMachineDetailFragmentArgs.fromBundle(requireArguments())
        val myVM = VirtualMachineMock().virtualMachines[args.vmId]
        val myVM = VirtualMachineMock().virtualMachines[args.id]
        binding.myVM = myVM

        binding.textviewName.text = "Name: ${myVM.name}"
        binding.textviewHostname.text = "Hostname: ${myVM.hostname}"
        binding.textviewFqdn.text = "FQDN: ${myVM.fqdn}"
        binding.textviewMode.text = "Mode: ${myVM.mode.printableName}"
        binding.textviewTemplate.text = "Template: ${myVM.mode.printableName}"

        var backupText = "Backup: "
        backupText += when(myVM.backupFrequency){
            0 -> "none"
            1 -> "daily"
            7 -> "weekly"
            14 -> "bi-weekly"
            30 -> "monthly"
            else -> "every ${myVM.backupFrequency} days"
        }
        binding.textviewBackup.text = backupText

        binding.textviewAvailability.text = "Availability: ${myVM.availability.printableName}"
        binding.textviewHost.text = "Host: ${myVM.hostname}"
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