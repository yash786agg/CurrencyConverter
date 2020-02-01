package com.app.currencyConverter.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RateList(val currency: String, val rate: Double) : Parcelable
