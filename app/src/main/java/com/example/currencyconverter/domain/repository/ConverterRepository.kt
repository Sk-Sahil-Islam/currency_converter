package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data.remote.dto.CurrenciesResponse
import com.example.currencyconverter.data.remote.dto.CurrencyRatesResponse

interface ConverterRepository {

    suspend fun getCurrencyList(): CurrenciesResponse

    suspend fun getCurrencyRates(base: String): CurrencyRatesResponse
}