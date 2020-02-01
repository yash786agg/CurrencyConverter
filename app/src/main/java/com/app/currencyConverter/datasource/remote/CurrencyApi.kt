package com.app.currencyConverter.datasource.remote

import com.app.currencyConverter.BuildConfig
import com.app.currencyConverter.commons.util.Constants.Companion.BASE_TAG
import com.app.currencyConverter.commons.util.Constants.Companion.LATEST_TAG
import com.app.currencyConverter.domain.entities.CurrencyRateData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET(BuildConfig.BASE_URL+LATEST_TAG)
    fun getCurrencyRateAsync(@Query(BASE_TAG) id: String) : Single<CurrencyRateData>
}