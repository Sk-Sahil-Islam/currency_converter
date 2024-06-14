package com.example.currencyconverter.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TargetCurrencyProvider @Inject constructor() {
    var targetCurrency: String = "usd" // Default value
}