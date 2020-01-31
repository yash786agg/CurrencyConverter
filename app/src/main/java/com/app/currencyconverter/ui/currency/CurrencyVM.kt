package com.app.currencyconverter.ui.currency

import androidx.lifecycle.MutableLiveData
import com.app.currencyconverter.commons.base.BaseViewModel
import com.app.currencyconverter.datasource.remote.NetworkState
import com.app.currencyconverter.domain.entities.CurrencyRateData
import com.app.currencyconverter.domain.usecase.CurrencyUseCase

class CurrencyVM(private val currencyUseCase: CurrencyUseCase) : BaseViewModel() {

    // OBSERVABLES ---
    var networkState : MutableLiveData<NetworkState<CurrencyRateData>> = MutableLiveData()

    fun handleCurrencyRate(currency : String) {
        addToDisposable(currencyUseCase.checkRates(currency))
        networkState = currencyUseCase.getNetworkState()
    }
}