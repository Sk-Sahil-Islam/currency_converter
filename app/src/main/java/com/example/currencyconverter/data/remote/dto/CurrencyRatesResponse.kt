package com.example.currencyconverter.data.remote.dto

import com.example.currencyconverter.data.remote.TargetCurrencyProvider
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

data class CurrencyRatesResponse(
    val baseCurrency: String,
    val targetCurrency: String,
    val rate: Double,
    val date: String
)


class CurrencyRatesResponseDeserializer(private val targetCurrencyProvider: TargetCurrencyProvider) : JsonDeserializer<CurrencyRatesResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CurrencyRatesResponse {
        val jsonObject = json?.asJsonObject!!
        val date = jsonObject.get("date").asString

        var baseCurrency = ""
        var rate = 0.0

        jsonObject.entrySet().forEach { entry ->
            if (entry.key != "date") {
                baseCurrency = entry.key
                val rates = entry.value.asJsonObject
                if (rates.has(targetCurrencyProvider.targetCurrency)) {
                    rate = rates.get(targetCurrencyProvider.targetCurrency).asDouble
                } else {
                    throw JsonParseException("Target currency ${targetCurrencyProvider.targetCurrency} not found in rates.")
                }
            }
        }

        return CurrencyRatesResponse(baseCurrency, targetCurrencyProvider.targetCurrency, rate, date)
    }
}