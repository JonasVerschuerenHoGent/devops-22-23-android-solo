package com.example.test.domain

import java.util.*
import kotlin.collections.ArrayList

class ProjectMock {
    var projects: ArrayList<Project>
    init{
        projects = createProjectMockArray()
    }
    private fun createProjectMockArray(): ArrayList<Project>{
        var random: Random = Random();

        var virtualMachineMock = VirtualMachineMock().virtualMachines
        var customerMock = CustomerMock().customers
        var stringArray = arrayListOf<String>("BachlorTDG", "Voka", "Unicef", "BachlorYVD")
        var statesArray = arrayListOf<String>("Proposal", "Approved", "Ongoing", "Terminated")
        var output = ArrayList<Project>()
        for(i in 0..3){
            output.add(
                Project(
                    id = 1,
                    name = stringArray[i],
                    virtualMachines = listOf(virtualMachineMock[random.nextInt(virtualMachineMock.size - 1)]),
                    customerId = 0,
                    customer = customerMock.get(i),
                    state = statesArray[i],
                    vmAmount = listOf<Int>(1, 2, 3, 4).get(random.nextInt(4)),
                    totalCpus = listOf<Int>(4, 8, 12, 16).get(random.nextInt(4)),
                    totalMemory = listOf<Int>(2, 4, 8, 32, 40).get(random.nextInt(5)),
                    totalStorage = listOf<Int>(128, 256, 384, 512).get(random.nextInt(4))
                )
            )
        }
        return output
    }
}