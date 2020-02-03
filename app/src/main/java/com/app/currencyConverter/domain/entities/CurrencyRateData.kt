package com.app.currencyConverter.domain.entities

import android.os.Parcelable
import com.app.currencyConverter.commons.util.Constants.Companion.BASE_TAG
import com.app.currencyConverter.commons.util.Constants.Companion.RATE_TAG
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyRateData(@field:Json(name = BASE_TAG) val base: String,
                            @field:Json(name = RATE_TAG) val rates: Map<String, Double>) : Parcelable