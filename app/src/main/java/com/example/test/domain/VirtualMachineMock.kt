package com.example.test.domain

import java.time.LocalDate
import java.time.ZoneId
import java.util.*


class VirtualMachineMock() {
    var virtualMachines: ArrayList<VirtualMachine>
    init{
        virtualMachines = createVirtualMachineMockArray()
    }
    private fun createVirtualMachineMockArray(): ArrayList<VirtualMachine>{
        var random: Random = Random();
        var members = MemberMock().members
        var customers = CustomerMock().customers
        var modes = Mode.values()
        var availabilities = Availability.values()
        var states = State.values()
        var project = Project(
            id = 1,
            name = "The VIC project",
            customerId = 1,
            state = "Ongoing"
        )
        var output = ArrayList<VirtualMachine>();


        for(i in 0..19){
            output.add(
                VirtualMachine(
                    id = i,
                    name = "VM-name-${i}",
                    projectId = 0,
                    project = project,
                    creatorId = 0,
                    creator = listOf(members[random.nextInt(members.size - 1)]).get(0),
                    state = states[random.nextInt(states.size-1)],
                    mode = modes[random.nextInt(modes.size-1)],
                    customerId = 0,
                    customer = listOf(customers[random.nextInt(customers.size - 1)]).get(0),
                    hostname = "HOSTNAME-${i}",
                    fqdn = "www.vic.com.",
                    vCPUAmount = 4,
                    memoryAmount = listOf<Int>(2, 4, 8, 16, 32).get(random.nextInt(5)),
                    storageAmount = listOf<Int>(128, 256, 512).get(random.nextInt(3)),
                    requestDate = giveDateWithDayOffset(-10),
                    beginDate = giveDateWithDayOffset(-5),
                    endDate = giveDateWithDayOffset(1),
                    backupFrequency = 7,
                    availability = availabilities[random.nextInt(availabilities.size-1)],
                    highAvailability = true,
                    hostServer = random.nextInt(13)+4,
                )
            )
        }
        return output
    }

    private fun giveDateWithDayOffset(offset: Long): LocalDate{
        return LocalDate.now().plusDays(offset)
    }

}