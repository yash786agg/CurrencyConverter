package com.app.currencyconverter.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rate(val symbol: String, val rate: Float) : Parcelable
