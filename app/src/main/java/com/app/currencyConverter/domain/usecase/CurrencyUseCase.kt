package com.app.currencyConverter.domain.usecase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.currencyConverter.datasource.remote.NetworkState
import com.app.currencyConverter.domain.entities.RateList
import com.app.currencyConverter.domain.repository.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    // FOR DATA ---
    private val networkState = MutableLiveData<NetworkState<ArrayList<RateList>>>()
    private val disposable = CompositeDisposable()
    private val TAG : String = "CurrencyActivity"

    fun checkRates(currency: String,amount: Double,isAmountUpdated : Boolean) : CompositeDisposable {

        Log.e(TAG,"CurrencyUseCase checkRates currency : $currency")
        Log.e(TAG,"CurrencyUseCase checkRates amount : $amount")
        Log.e(TAG,"CurrencyUseCase checkRates isAmountUpdated : $isAmountUpdated")

        if(isAmountUpdated) disposable.clear()

        disposable.add(currencyRepository.getRates(currency)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeatUntil {!currency.equals(currency, ignoreCase = true) }
            .subscribe({

                val rates = ArrayList<RateList>()
                rates.add(RateList(currency, amount))
                rates.addAll(it.rates.map { data -> RateList(data.key, data.value * amount) })

                networkState.postValue(NetworkState.Success(rates))

                Log.e(TAG,"CurrencyUseCase checkRates Success base: ${it.base}")
                Log.e(TAG,"CurrencyUseCase checkRates Success rates: ${it.rates}")

            }, {
                Log.e(TAG,"CurrencyUseCase checkRates Error: ${it.message}")
                networkState.postValue(NetworkState.Error(it.message,null))
            }))

        return disposable
    }

    fun getNetworkState(): MutableLiveData<NetworkState<ArrayList<RateList>>> = networkState
}