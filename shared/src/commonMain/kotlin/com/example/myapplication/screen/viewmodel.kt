package com.example.myapplication.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myapplication.view.DropdownTextFieldViewItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardScreenState())
    val uiState: StateFlow<DashboardScreenState> = _uiState

    var fromAmount by mutableStateOf("")
        private set

    var toAmount by mutableStateOf("")
        private set

    var purpose by mutableStateOf("")
        private set

    var purposePayment by mutableStateOf("")
        private set

    fun changeFromAmount(input: String) {
        fromAmount = input
        calcFromAmount()
    }

    fun changeFromCurrency(input: DropdownTextFieldViewItem) {
        _uiState.value = _uiState.value.copy(fromCurrency = input.toCurrency())
        calcFromAmount()
    }

    fun changeToAmount(input: String) {
        toAmount = input
        fromAmount = calcRate(toAmount, _uiState.value.rateRevert())
    }

    fun changeToCurrency(input: DropdownTextFieldViewItem) {
        _uiState.value = _uiState.value.copy(toCurrency = input.toCurrency())
        calcFromAmount()
    }

    fun changePurpose(input: String) {
        purpose = input
    }

    fun changePaymentPurpose(input: String) {
        purposePayment = input
    }

    fun changeTransferOption(isRegular: Boolean) {
        _uiState.value = _uiState.value.copy(isRegular = isRegular)
    }

    fun transfer() {
        println(_uiState.value.toString())
    }

    private fun calcFromAmount(){
        toAmount = calcRate(fromAmount, _uiState.value.rateCurrent())
    }

    private fun calcRate(amount: String, rate: ExchangeRate?): String {
        if (rate == null) return amount
        val parsed = amount.replace(",", "").toDoubleOrNull()
        if (parsed != null) {
            return "${parsed * rate.rate}"
        }
        return amount
    }
}