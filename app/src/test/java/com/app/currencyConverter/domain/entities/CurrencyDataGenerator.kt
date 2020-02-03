package com.app.currencyConverter.domain.entities

object CurrencyDataGenerator {

    fun getSuccessCurrencyData() : ArrayList<RateList> {

        val rates = ArrayList<RateList>()

        rates.add(RateList("AUD", 1.6114))
        rates.add(RateList("BGN", 1.9497))
        rates.add(RateList("BRL", 4.7769))
        rates.add(RateList("CAD", 1.529))
        rates.add(RateList("CHF", 1.124))

        return rates
    }
}