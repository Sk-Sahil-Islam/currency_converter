package com.example.currencyconverter.presentation.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val rate: String = ""
)
