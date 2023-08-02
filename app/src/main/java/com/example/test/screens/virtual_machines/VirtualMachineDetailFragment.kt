package com.example.test.screens.virtual_machines

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineDetailBinding
import com.example.test.domain.VirtualMachineMock
import java.time.LocalDate

class VirtualMachineDetailFragment : Fragment() {

    //binding
    private lateinit var binding: FragmentVirtualMachineDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val args = VirtualMachineDetailFragmentArgs.fromBundle(requireArguments())

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_virtual_machine_detail,
            container,
            false
        )

        val myVM = VirtualMachineMock().virtualMachines[args.vmId]
        //binding.myVm = myVM

        //code for overflow menu
        val menuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.overflow_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.listCustomersFragment){
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
        }, viewLifecycleOwner)

        //viewModel
        val application = requireNotNull(this.activity).application
        val viewModelFactory = VirtualMachineViewModelFactory(application, args.vmId)
        val viewModel: VirtualMachineViewModel by viewModels{viewModelFactory}

        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        binding.virtualMachineViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        //binding.lifecycleOwner = this



        //some manual binding stuff
        //============================================
        var backupText = ""
        backupText += when(myVM.backupFrequency){
            "0" -> "none"
            "1" -> "daily"
            "7" -> "weekly"
            "14" -> "bi-weekly"
            "30" -> "monthly"
            else -> "every ${myVM.backupFrequency} days"
        }
        binding.textviewBackup.text = backupText


        if(myVM.beginDate <= LocalDate.now() && //startDate is in past
            myVM.endDate > LocalDate.now()&& //endDate is in future
            myVM.beginDate < myVM.endDate//startdate is before enddate
        ){
            binding.textviewState.text =  "actief"
        }
        else{
            binding.textviewState.text = "inactief"
        }
        //============================================

        binding.lifecycleOwner = this

        return binding.root
    }
}