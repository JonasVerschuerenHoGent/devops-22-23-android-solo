package com.example.test.utils

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitBuilder {
    companion object Factory {
        private lateinit var retrofitInstance: Retrofit

        fun getInstance(): Retrofit {
            if (!this::retrofitInstance.isInitialized) {
                retrofitInstance = Retrofit.Builder()
                    .baseUrl("https://simplifiedcoding.net/demos/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
            }
            return retrofitInstance
        }
    }
}


