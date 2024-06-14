package com.example.currencyconverter.di

import com.example.currencyconverter.common.Constant
import com.example.currencyconverter.data.remote.ConverterService
import com.example.currencyconverter.data.remote.TargetCurrencyProvider
import com.example.currencyconverter.data.remote.dto.CurrencyRatesResponse
import com.example.currencyconverter.data.remote.dto.CurrencyRatesResponseDeserializer
import com.example.currencyconverter.data.repository.ConverterRepositoryImpl
import com.example.currencyconverter.domain.repository.ConverterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTargetCurrencyProvider(): TargetCurrencyProvider {
        return TargetCurrencyProvider()
    }

    @Provides
    @Singleton
    fun provideGson(targetCurrencyProvider: TargetCurrencyProvider): Gson {
        return GsonBuilder()
            .registerTypeAdapter(CurrencyRatesResponse::class.java, CurrencyRatesResponseDeserializer(targetCurrencyProvider))
            .create()
    }

    @Provides
    @Singleton
    fun provideConverterService(okHttpClient: OkHttpClient, gson: Gson): ConverterService {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ConverterService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(service: ConverterService): ConverterRepository {
        return ConverterRepositoryImpl(service)
    }
}