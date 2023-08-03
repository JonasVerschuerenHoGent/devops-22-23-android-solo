package com.example.test.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


enum class VicApiStatus { LOADING, ERROR, DONE }

private const val BASE_URL = "https://10.0.2.2:5001/api/"
interface VicApiService {

    //Customers
    @GET("customer")
    fun getAllCustomers():
            Deferred<NetworkCustomerListWrapper>
    @GET("customer/{id}")
    fun getCustomerByid(@Path("id") id:Int):
            Deferred<NetworkCustomerSingleWrapper>

    //Members
    @GET("member")
    fun getAllMembers():
            Deferred<NetworkMemberListWrapper>
    @GET("member/{id}")
    fun getMemberByid(@Path("id") id:Int):
            Deferred<NetworkMemberSingleWrapper>

    //Projects
    @GET("project")
    fun getAllProjects():
            Deferred<NetworkProjectListWrapper>
    @GET("project/{id}")
    fun getProjectByid(@Path("id") id:Int):
            Deferred<NetworkProjectSingleWrapper>

    //Virtual Machines
    @GET("VirtualMachine")
    fun getAllVirtualMachines():
            Deferred<NetworkVirtualMachineListWrapper>
    @GET("VirtualMachine/{id}")
    fun getVirtualMachineByid(@Path("id") id:Int):
            Deferred<NetworkVirtualMachineSingleWrapper>

}


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



object VicApi {
    private val retrofit = Retrofit.Builder()
        .client(getUnsafeOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: VicApiService by lazy {
        retrofit.create(VicApiService::class.java)
    }
}







//Function that allows us to speak to the client
private fun getUnsafeOkHttpClient(): OkHttpClient? {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?,
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            }
        )
        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()

        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier(object : HostnameVerifier {
            override fun verify(hostname: String?, session: SSLSession?): Boolean {
                return true
            }
        })
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}