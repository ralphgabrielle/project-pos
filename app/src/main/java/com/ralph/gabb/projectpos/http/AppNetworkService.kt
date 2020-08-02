package com.ralph.gabb.projectpos.http

import com.ralph.gabb.projectpos.BuildConfig
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface AppNetworkService {

    @POST("d39167a4-4ce7-4fa4-b83a-d0393003cd29")
    suspend fun loginAccount(@Body loginBody: LoginBody): Wrapper<LoginResponse>

    @POST("fd99e8f3-8291-458c-adcc-7ea668ba88a6")
    suspend fun fetchCategories(@Body tokenBody: TokenBody): Wrapper<CategoryResponse>

    @POST("2aba84d6-ec21-49eb-8a73-9c7365699ef8")
    suspend fun fetchProducts(@Body tokenBody: TokenBody): Wrapper<ProductResponse>

    companion object {
        operator fun invoke(): AppNetworkService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(BodyInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppNetworkService::class.java)
        }
    }
}