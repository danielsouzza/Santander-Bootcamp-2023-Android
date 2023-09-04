package com.example.devapp.data.network

import com.example.devapp.data.model.Repository
import com.example.devapp.data.model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface AppServe {
    companion object{
        private const val BASE_URL = "https://api.github.com/"
        val providerRetrofit: AppServe =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        ).build()
                ).build().create(AppServe::class.java)

    }

    @GET("users/{username}")
    suspend fun getUser(@Path(value = "username") username:String): User

    @GET("users/{username}/repos")
    suspend fun getRepositories(@Path(value = "username") username:String): List<Repository>
}