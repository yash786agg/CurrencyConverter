package com.app.currencyConverter.commons.util

import android.content.Context
import com.app.currencyConverter.R

class CurrencyUtil {
    companion object {

        fun getFlagImageResId(context: Context, code: String?): Int {

            when (code) {
                context.getString(R.string.aud) -> return R.drawable.ic_flag_aud
                context.getString(R.string.bgn) -> return R.drawable.ic_flag_bgn
                context.getString(R.string.brl) -> return R.drawable.ic_flag_brl
                context.getString(R.string.cad) -> return R.drawable.ic_flag_cad
                context.getString(R.string.chf) -> return R.drawable.ic_flag_chf
                context.getString(R.string.cny) -> return R.drawable.ic_flag_cny
                context.getString(R.string.czk) -> return R.drawable.ic_flag_czk
                context.getString(R.string.dkk) -> return R.drawable.ic_flag_dkk
                context.getString(R.string.eur) -> return R.drawable.ic_flag_eur
                context.getString(R.string.gbp) -> return R.drawable.ic_flag_gbp
                context.getString(R.string.hkd) -> return R.drawable.ic_flag_hkd
                context.getString(R.string.hrk) -> return R.drawable.ic_flag_hrk
                context.getString(R.string.huf) -> return R.drawable.ic_flag_huf
                context.getString(R.string.idr) -> return R.drawable.ic_flag_idr
                context.getString(R.string.ils) -> return R.drawable.ic_flag_ils
                context.getString(R.string.inr) -> return R.drawable.ic_flag_inr
                context.getString(R.string.isk) -> return R.drawable.ic_flag_isk
                context.getString(R.string.jpy) -> return R.drawable.ic_flag_jpy
                context.getString(R.string.krw) -> return R.drawable.ic_flag_krw
                context.getString(R.string.mxn) -> return R.drawable.ic_flag_mxn
                context.getString(R.string.myr) -> return R.drawable.ic_flag_myr
                context.getString(R.string.nok) -> return R.drawable.ic_flag_nok
                context.getString(R.string.nzd) -> return R.drawable.ic_flag_nzd
                context.getString(R.string.php) -> return R.drawable.ic_flag_php
                context.getString(R.string.pln) -> return R.drawable.ic_flag_pln
                context.getString(R.string.ron) -> return R.drawable.ic_flag_ron
                context.getString(R.string.rub) -> return R.drawable.ic_flag_rub
                context.getString(R.string.sek) -> return R.drawable.ic_flag_sek
                context.getString(R.string.sgd) -> return R.drawable.ic_flag_sgd
                context.getString(R.string.thb) -> return R.drawable.ic_flag_thb
                context.getString(R.string.ctry) -> return R.drawable.ic_flag_try
                context.getString(R.string.usd) -> return R.drawable.ic_flag_usd
                context.getString(R.string.zar) -> return R.drawable.ic_flag_zar
                else -> return R.drawable.ic_flag_placeholder
            }
        }

        fun rateStringToDouble(value: String): Double {
            if(value.isEmpty() || value.toDoubleOrNull() == 0.0 || value == ".") {
                return 1.0
            }
            return value.toDouble()
        }
    }
}