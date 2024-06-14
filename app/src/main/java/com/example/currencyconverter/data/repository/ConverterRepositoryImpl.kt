package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.remote.ConverterService
import com.example.currencyconverter.data.remote.dto.CurrenciesResponse
import com.example.currencyconverter.data.remote.dto.CurrencyRatesResponse
import com.example.currencyconverter.domain.repository.ConverterRepository

class ConverterRepositoryImpl(
    private val converterService: ConverterService
): ConverterRepository {
    override suspend fun getCurrencyList(): CurrenciesResponse {
        return converterService.getCurrencyList()
    }

    override suspend fun getCurrencyRates(base: String): CurrencyRatesResponse {
        return converterService.getCurrencyRates(base)
    }
}