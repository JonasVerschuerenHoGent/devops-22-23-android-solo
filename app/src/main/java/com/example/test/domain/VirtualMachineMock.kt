package com.example.test.domain

import java.util.*
import kotlin.collections.ArrayList
import java.util.Calendar;
import java.util.Date;



class VirtualMachineMock() {
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
                 requestDate = Calendar.getInstance().time,
                 beginDate = Calendar.getInstance().time,
                 endDate = Calendar.getInstance().time,


                    )
            )
        }
        return output
    }
}