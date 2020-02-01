package com.app.currencyConverter.commons.extension

import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.request.CachePolicy
import com.app.currencyConverter.R
import com.app.currencyConverter.commons.util.CurrencyUtil.Companion.getFlagImageResId

@BindingAdapter(value = ["flagImg"])
fun flgImg(view: ImageView, country : String?) {

    if(!TextUtils.isEmpty(country)) {

        val countryCode = getFlagImageResId(view.context,country)

        view.load(countryCode) {
            crossfade(true)
            placeholder(R.drawable.ic_flag_placeholder)
            error(R.drawable.ic_flag_placeholder)
            memoryCachePolicy(CachePolicy.DISABLED)
        }
    }
}

fun EditText.moveCursorToProperPosition() {
    this.setSelection(this.text.length)
}