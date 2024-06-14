package com.example.currencyconverter.data.remote

import com.example.currencyconverter.data.remote.dto.CurrenciesResponse
import com.example.currencyconverter.data.remote.dto.CurrencyRatesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ConverterService {

    @GET("/currency-api@latest/v1/currencies.min.json")
    suspend fun getCurrencyList(): CurrenciesResponse

    @GET("/currency-api@latest/v1/currencies/{base}.min.json")
    suspend fun getCurrencyRates(
        @Path("base") base: String
    ): CurrencyRatesResponse

}