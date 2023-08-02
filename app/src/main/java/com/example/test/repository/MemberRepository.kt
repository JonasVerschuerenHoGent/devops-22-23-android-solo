package com.example.test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.test.database.VicDatabase
import com.example.test.database.member.asDomainModel
import com.example.test.domain.Member
import com.example.test.network.NetworkMember
import com.example.test.network.NetworkMemberContainer
import com.example.test.network.VicApi
import com.example.test.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberRepository(private val database: VicDatabase) {

    //Members attribute that can be shown on screen.
    val members: LiveData<List<Member>> = Transformations.map(database.memberDatabaseDao.getAllMembers()) {
        it.asDomainModel()
    }


    //Refreshes the Members
    suspend fun refreshMembers() {
        withContext(Dispatchers.IO) {
            val detailedMembers = ArrayList<NetworkMember>()
            val members = VicApi.retrofitService.getAllMembers().await()
            members.forEach {
                val m = VicApi.retrofitService.getMemberByid(it.id).await()
                detailedMembers.add(m)
            }
            database.memberDatabaseDao.insertAll(*NetworkMemberContainer(detailedMembers).asDatabaseModel())
        }
    }


}