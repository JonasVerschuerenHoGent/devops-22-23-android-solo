package com.example.test.domain


import java.util.*
import kotlin.collections.ArrayList

class CustomerMock() {
    var customers: ArrayList<Customer>
    init{
        customers = createUserMockArray()
    }
    private fun createUserMockArray(): ArrayList<Customer>{
        var random: Random = Random();
        var departments = Department.values()
        var output = ArrayList<Customer>();

        for(i in 0..19){
            output.add(Customer(
                i,
                name = "Account ${i}",
                email = "Email ${i}",
                phoneNr = "Phone number ${i}",
                department = departments.get(random.nextInt(departments.size-1)),
                externType = "Extern type ${i}",
                education = "Education ${i}",
                backupContactId = -1
            ))
        }
        return output
    }
}