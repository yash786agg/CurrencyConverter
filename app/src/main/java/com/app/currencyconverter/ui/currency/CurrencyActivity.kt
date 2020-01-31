package com.app.currencyconverter.ui.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.app.currencyconverter.R
import com.app.currencyconverter.commons.util.Constants.Companion.EUR_BASE_VALUE
import com.app.currencyconverter.commons.util.UiHelper
import com.app.currencyconverter.datasource.remote.NetworkState
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyActivity : AppCompatActivity() {

    private val currencyVM : CurrencyVM by viewModel()
    private val uiHelper : UiHelper by inject()
    private val TAG : String = "CurrencyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(uiHelper.getConnectivityStatus()) {
            currencyVM.handleCurrencyRate(EUR_BASE_VALUE)

            /*
             * Progress Updater
             * */
            currencyVM.networkState.observe(this, Observer {

                it?.let {
                    when(it) {
                        is NetworkState.Loading -> Log.e(TAG,"CurrencyActivity Loading")
                        is NetworkState.Success -> Log.e(TAG,"CurrencyActivity Success: ${it.data}")
                        is NetworkState.Error -> Log.e(TAG,"CurrencyActivity Error: ${it.message}")
                    }
                }
            })
        }
    }
}
