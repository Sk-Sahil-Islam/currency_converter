package com.example.currencyconverter.presentation.home

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Convert: HomeUiState()
    data class Success(val data: String) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
