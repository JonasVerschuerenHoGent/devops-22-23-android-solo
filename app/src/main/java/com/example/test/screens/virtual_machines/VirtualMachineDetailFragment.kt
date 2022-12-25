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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import com.example.test.databinding.FragmentVirtualMachineDetailBinding
import com.example.test.domain.VirtualMachine
import java.time.LocalDate

class VirtualMachineDetailFragment : Fragment() {


    //binding
    private lateinit var binding: FragmentVirtualMachineDetailBinding
    private lateinit var myVM : VirtualMachine

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val args = VirtualMachineDetailFragmentArgs.fromBundle(requireArguments())

        val viewModel: VirtualMachineViewModel by lazy {
            val activity = requireNotNull(this.activity)
            ViewModelProvider(this, VirtualMachineViewModelFactory(activity.application, args.vmId)).get(
                VirtualMachineViewModel::class.java)
        }

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_virtual_machine_detail,
            container,
            false
        )

        if(viewModel.virtualMachine != null) {
            myVM = viewModel.virtualMachine.value!!
        }

        //viewModel

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
            0 -> "none"
            1 -> "daily"
            7 -> "weekly"
            14 -> "bi-weekly"
            30 -> "monthly"
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