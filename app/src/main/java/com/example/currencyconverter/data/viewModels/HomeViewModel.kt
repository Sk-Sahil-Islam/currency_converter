package com.example.currencyconverter.data.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.remote.TargetCurrencyProvider
import com.example.currencyconverter.domain.repository.ConverterRepository
import com.example.currencyconverter.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repository: ConverterRepository,
    private val targetCurrencyProvider: TargetCurrencyProvider
): ViewModel() {
    private val _homeState = MutableStateFlow(HomeUiState())
    val homeState: StateFlow<HomeUiState> = _homeState


    fun getCurrencies(baseCurrency: String, targetCurrency: String, enteredAmount: String) = viewModelScope.launch {
        _homeState.value = HomeUiState(isLoading = true)
        try {
            targetCurrencyProvider.targetCurrency = targetCurrency
            val response = repository.getCurrencyRates(baseCurrency)
            val rate = response.rate
            val result = rate * enteredAmount.toDouble()
            _homeState.value = HomeUiState(rate = String.format(Locale.UK, "%.2f", result))
        } catch (e: Exception) {
            _homeState.value = HomeUiState(error = e.message ?: "An error occurred")
        }
    }
}