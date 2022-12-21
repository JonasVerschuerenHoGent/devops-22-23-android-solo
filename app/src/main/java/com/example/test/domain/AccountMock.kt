package com.example.test.domain


import com.example.test.domain.Account
import com.example.test.domain.Department
import com.example.test.domain.Role
import java.util.*
import kotlin.collections.ArrayList

class AccountMock() {
    var users: ArrayList<Account>
    init{
        users = createUserMockArray()
    }
    private fun createUserMockArray(): ArrayList<Account>{
        var random: Random = Random();

        var departments = Department.values()
        var roles = Role.values()

        var output = ArrayList<Account>();

        for(i in 0..19){
            output.add(Account(
                i,
                "Email ${i}",
                "Account ${i}",
                departments[random.nextInt(departments.size-1)],
                "Education ${i}",
                "Extern type ${i}",
                "Phone number ${i}",
                roles[random.nextInt(roles.size-1)],
                "Backup contact ${i}"
            ))
        }
        return output
    }
}