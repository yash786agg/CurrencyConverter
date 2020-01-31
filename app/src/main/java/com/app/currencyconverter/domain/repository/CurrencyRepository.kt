package com.app.currencyconverter.domain.repository

import com.app.currencyconverter.datasource.remote.CurrencyApi
import com.app.currencyconverter.domain.entities.CurrencyRateData
import io.reactivex.Single

class CurrencyRepository(private val currencyApi: CurrencyApi)  {

    fun getRates(base: String): Single<CurrencyRateData> {
        return currencyApi.getCurrencyRateAsync(base)
    }
}