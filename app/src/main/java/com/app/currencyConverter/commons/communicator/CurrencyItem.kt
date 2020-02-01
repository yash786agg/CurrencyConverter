package com.app.currencyConverter.commons.communicator

interface CurrencyItem {
    fun onAmountChanged(currency: String, amount: Double)
}