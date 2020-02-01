package com.app.currencyConverter.domain.repository

import com.app.currencyConverter.datasource.remote.CurrencyApi
import com.app.currencyConverter.domain.entities.CurrencyRateData
import io.reactivex.Single

class CurrencyRepository(private val currencyApi: CurrencyApi)  {

    fun getRates(base: String): Single<CurrencyRateData> {
        return currencyApi.getCurrencyRateAsync(base)
    }
}