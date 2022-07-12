package com.example.strangernews.data.di

import com.example.strangernews.data.source.remote.api.ApiService
import com.example.strangernews.utils.constant.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Single
    fun provideInterceptor(): Interceptor = Interceptor {
        val originalRequest = it.request()
        val newRequest = originalRequest.newBuilder().build()
        return@Interceptor it.proceed(newRequest)
    }

    @Single
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClientBuilder
            .addInterceptor(interceptor)
            .addInterceptor(logging)
            .build()
        return httpClientBuilder.build()
    }

    @Single
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Single
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
