package com.example.currencyconverter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.remote.TargetCurrencyProvider
import com.example.currencyconverter.domain.repository.ConverterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: ConverterRepository,
    private val targetCurrencyProvider: TargetCurrencyProvider
): ViewModel() {


    init {
        targetCurrencyProvider.targetCurrency = "inr"
        getCurrencies()

    }

    fun getCurrencies() = viewModelScope.launch {

        val result = repository.getCurrencyRates("eur")
        Log.e("TestViewModel", "getCurrencies: $result")
    }
}