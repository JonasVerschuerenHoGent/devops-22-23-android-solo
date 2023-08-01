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
                    state = statesArray[i]
                )
            )
        }
        return output
    }
}