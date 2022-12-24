package com.example.test.network.interfaces

import com.example.test.domain.VirtualMachine
import com.example.test.network.ApiVirtualMachine
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path



private var API_BASE_URL = "127.0.0.1:3000/customer"

// Create Moshi object
private val moshi = Moshi.Builder()
  .add(KotlinJsonAdapterFactory())
  .build()

// An OkHttp interceptor which logs request and response information
private val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

// OkHttpClient that uses the logger interceptor
private val client = OkHttpClient.Builder()
  .addInterceptor(logger)
  .build()

interface VirtualMachineApiService {

  @GET("virtualmachines/{id}")
  fun getVirtualMachineByID(@Path("id") id:String) : Deferred<ApiVirtualMachine>
  @GET("virtualmachines/project/{projectId}")
  fun getVirtualMachineByProject(@Path("projectId") projectId:String) : Deferred<List<ApiVirtualMachine>>
  @GET("virtualmachines")
  fun getVirtualMachines() : Deferred<List<ApiVirtualMachine>>

}

private val retrofit = Retrofit.Builder()
  .addConverterFactory(MoshiConverterFactory.create(moshi))
  .addCallAdapterFactory(CoroutineCallAdapterFactory())
  .baseUrl(API_BASE_URL)
  .client(client)
  .build()

object ApiVirtualMachineObj {
  // Lazy properties with delegate: thread safe, can only be initialized once at a time. Adds extra safety to our 1 instance we need
  val retrofitService: VirtualMachineApiService by lazy {
    retrofit.create(VirtualMachineApiService::class.java)
  }
}