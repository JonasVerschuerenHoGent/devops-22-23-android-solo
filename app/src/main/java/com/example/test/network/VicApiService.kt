package com.example.test.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


enum class VicApiStatus { LOADING, ERROR, DONE }

private const val BASE_URL = "https://localhost:5001/api/"
interface VicApiService {

    //Customers
    @GET("Customer")
    fun getAllCustomers():
            Deferred<List<NetworkCustomer>>
    @GET("Customer/{id}")
    fun getCustomerByid(@Path("id") id:Int):
            Deferred<NetworkCustomer>

    //Members
    @GET("Member")
    fun getAllMembers():
            Deferred<List<NetworkMember>>
    @GET("Member/{id}")
    fun getMemberByid(@Path("id") id:Int):
            Deferred<NetworkMember>

    //Projects
    @GET("Project")
    fun getAllProjects():
            Deferred<List<NetworkProject>>
    @GET("Project/{id}")
    fun getProjectByid(@Path("id") id:Int):
            Deferred<NetworkProject>

    //Virtual Machines
    @GET("VirtualMachine")
    fun getAllVirtualMachines():
            Deferred<List<NetworkVirtualMachine>>
    @GET("VirtualMachine/{id}")
    fun getVirtualMachineByid(@Path("id") id:Int):
            Deferred<NetworkVirtualMachine>

}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object VicApi {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: VicApiService by lazy {
        retrofit.create(VicApiService::class.java)
    }
}