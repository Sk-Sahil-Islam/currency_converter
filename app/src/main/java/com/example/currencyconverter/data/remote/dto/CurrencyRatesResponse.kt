package com.example.currencyconverter.data.remote.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class CurrencyRatesResponse(
    val baseCurrency: String,
    val rates: Rates,
    val date: String
)

class CurrencyRatesResponseDeserializer : JsonDeserializer<CurrencyRatesResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): CurrencyRatesResponse {
        val jsonObject = json?.asJsonObject!!
        val date = jsonObject.get("date").asString

        var baseCurrency = ""
        var rates: Rates? = null

        jsonObject.entrySet().forEach { entry ->
            if (entry.key != "date") {
                baseCurrency = entry.key
                rates = context!!.deserialize(entry.value, Rates::class.java)
            }
        }

        return CurrencyRatesResponse(baseCurrency, rates!!, date)
    }
}