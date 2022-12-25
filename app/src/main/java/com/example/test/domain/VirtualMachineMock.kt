package com.example.test.domain

import java.time.LocalDate
import java.time.ZoneId
import java.util.*


class VirtualMachineMock() {/*
    var virtualMachines: ArrayList<VirtualMachine>
    init{
        virtualMachines = createVirtualMachineMockArray()
    }
    private fun createVirtualMachineMockArray(): ArrayList<VirtualMachine>{
        var random: Random = Random();
        var modes = Mode.values()
        var availabilities = Availability.values()
        var states = State.values()

        var output = ArrayList<VirtualMachine>();

        for(i in 0..19){
            output.add(
                VirtualMachine(
                "VM-name-${i}",
                i,
                "HOSTNAME-${i}",
                "abcdef",
                modes[random.nextInt(modes.size-1)],
                random.nextInt(8),
                availabilities[random.nextInt(availabilities.size-1)],
                random.nextInt(6),
                random.nextInt(5),
                intArrayOf(80, 22, 5000),
                states[random.nextInt(states.size-1)],
                random.nextInt(8)+1,
                random.nextInt(13)+4,
                128,
                true,
                 requestDate = giveDateWithDayOffset(-10),//Calendar.getInstance().time,
                 beginDate = giveDateWithDayOffset(-5),//Calendar.getInstance().time,
                 endDate = giveDateWithDayOffset(1)//Calendar.getInstance().time,
                //change these^ offsets to play around with active/inactive vms
                )
            )
        }
        return output
    }

    private fun giveDateWithDayOffset(offset: Long): LocalDate{
        return LocalDate.now().plusDays(offset)
    }
*/
}