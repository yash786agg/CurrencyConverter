package com.app.currencyConverter.ui.currency

import androidx.lifecycle.MutableLiveData
import com.app.currencyConverter.commons.base.BaseViewModel
import com.app.currencyConverter.datasource.remote.NetworkState
import com.app.currencyConverter.domain.entities.RateList
import com.app.currencyConverter.domain.usecase.CurrencyUseCase

class CurrencyVM(private val currencyUseCase: CurrencyUseCase) : BaseViewModel() {

    // OBSERVABLES ---
    var networkState : MutableLiveData<NetworkState<ArrayList<RateList>>> = MutableLiveData()

    fun handleCurrencyRate(currency : String, amount: Double,isAmountUpdated : Boolean) {
        addToDisposable(currencyUseCase.checkRates(currency,amount,isAmountUpdated))
        networkState = currencyUseCase.getNetworkState()
    }
}