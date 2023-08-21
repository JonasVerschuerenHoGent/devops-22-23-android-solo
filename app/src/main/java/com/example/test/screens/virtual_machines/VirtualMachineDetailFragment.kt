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
import java.time.LocalDateTime

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


        binding.lifecycleOwner = this
        return binding.root
    }
}