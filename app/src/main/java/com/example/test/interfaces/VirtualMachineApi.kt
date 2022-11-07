package com.example.test.interfaces

import com.example.test.domain.VirtualMachine
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface VirtualMachineApi {

  @GET("virtualmachines/{id}")
  fun getVirtualMachineByID(@Path("id") id:String) : Call<VirtualMachine>
  @GET("virtualmachines/project/{projectId}")
  fun getVirtualMachineByProject(@Path("projectId") projectId:String) : Call<List<VirtualMachine>>


}