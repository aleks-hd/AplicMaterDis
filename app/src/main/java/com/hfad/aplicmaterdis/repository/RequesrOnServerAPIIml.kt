package com.hfad.aplicmaterdis.repository

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RequesrOnServerAPIIml {

    private val baseURL = "https://api.nasa.gov/"

    fun getRetrofit(): RequesrOnServerAPI {
        val pRetrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(createOkHttpClient(PODInterseptor()))
            .build()
        return pRetrofit.create(RequesrOnServerAPI::class.java)
    }

    inner class PODInterseptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            return chain.proceed(chain.request())
        }

    }

    private fun createOkHttpClient(interseptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interseptor)

        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

}

