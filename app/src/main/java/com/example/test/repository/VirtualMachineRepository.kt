package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.VicDatabase
import com.example.test.database.VirutalMachine.asDomainModel
import com.example.test.domain.VirtualMachine
import com.example.test.network.NetworkVirtualMachine
import com.example.test.network.NetworkVmContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VirtualMachineRepository(private val database: VicDatabase) {

    //VirtualMachines attribute that can be shown on screen.
    val virtualMachines: LiveData<List<VirtualMachine>> = Transformations.map(database.virtualMachineDatabaseDao.getAllVirtualMachines()) {
        it.asDomainModel()
    }


    //Refreshes the VirtualMachines
    suspend fun refreshVirtualMachines() {
        withContext(Dispatchers.IO) {
            val detailedVirtualMachines = ArrayList<NetworkVirtualMachine>()
            val virtualmachines = VicApi.retrofitService.getAllVirtualMachines().await()
            virtualmachines.forEach {
                val vm = VicApi.retrofitService.getVirtualMachineByid(it.id).await()
                detailedVirtualMachines.add(vm)
            }
            database.virtualMachineDatabaseDao.insertAll(*NetworkVmContainer(detailedVirtualMachines).asDatabaseModel())
        }
    }


}