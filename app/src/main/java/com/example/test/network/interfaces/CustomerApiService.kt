package com.example.test.network.interfaces

import com.example.test.domain.Project
import com.example.test.network.ApiCustomer
import com.example.test.network.ApiProject
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory.*
import retrofit2.http.GET
import retrofit2.http.Path



private var API_BASE_URL = "http://18.209.147.83:443/api/"

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

interface CustomerApiService {


    @GET("customers/")
    fun getAllCustomers() : Deferred<List<ApiCustomer>>
    @GET("customers/{id}")
    fun getCustomerById(@Path("id") role:String) : Deferred<List<ApiCustomer>>
}

private val retrofit = Retrofit.Builder()
    .addConverterFactory(create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(API_BASE_URL)
    .client(client)
    .build()

object ApiCustomerObj {
    // Lazy properties with delegate: thread safe, can only be initialized once at a time. Adds extra safety to our 1 instance we need
    val retrofitService: CustomerApiService by lazy {
        retrofit.create(CustomerApiService::class.java)
    }
}