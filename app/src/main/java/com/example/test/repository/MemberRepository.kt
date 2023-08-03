package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.test.database.VicDatabase
import com.example.test.database.VirutalMachine.asDomainModel
import com.example.test.database.customer.asDomainModel
import com.example.test.database.member.asDomainModel
import com.example.test.domain.Customer
import com.example.test.domain.Member
import com.example.test.domain.VirtualMachine
import com.example.test.network.NetworkMember
import com.example.test.network.NetworkMemberContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberRepository(private val database: VicDatabase) {
    var memberId: Int? = -1

    //Members attribute that can be shown on screen.
    val members: LiveData<List<Member>> = Transformations.map(database.memberDatabaseDao.getAllMembers()) {
        it.asDomainModel()
    }

    val member: LiveData<Member> = Transformations.map(database.memberDatabaseDao.getMemberById(memberId!!)) {
        it.asDomainModel()
    }

    fun getVirtualMachinesOfMember(id: Int): LiveData<List<VirtualMachine>> {
        return database.virtualMachineDatabaseDao.getVmsFromMemberId(id)!!.map { it.asDomainModel() }
    }


    //Refreshes the Members
    suspend fun refreshMembers() {
        withContext(Dispatchers.IO) {
            val detailedMembers = ArrayList<NetworkMember>()
            val membersWrapper = VicApi.retrofitService.getAllMembers().await()
            membersWrapper.members.forEach {
                val m = VicApi.retrofitService.getMemberByid(it.id).await()
                detailedMembers.add(m.member)
            }
            database.memberDatabaseDao.insertAll(*NetworkMemberContainer(detailedMembers).asDatabaseModel())
        }
    }


}