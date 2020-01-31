package com.app.currencyconverter.domain.usecase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.currencyconverter.datasource.remote.NetworkState
import com.app.currencyconverter.domain.entities.CurrencyRateData
import com.app.currencyconverter.domain.repository.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CurrencyUseCase(private val currencyRepository: CurrencyRepository) {

    // FOR DATA ---
    private val networkState = MutableLiveData<NetworkState<CurrencyRateData>>()
    private var currentBase: String = ""
    private var viewStopped = false
    private val disposable = CompositeDisposable()
    private val TAG : String = "CurrencyActivity"

    fun checkRates(currency: String) : CompositeDisposable {

        Log.e(TAG,"CurrencyUseCase checkRates currency : $currency")

        /*disposable.add( currencyRepository.getRates(Constants.EUR_BASE_VALUE).with()
            .delay(1, TimeUnit.SECONDS)
            .doOnSubscribe { networkState.postValue(NetworkState.Loading()) }
            .doOnSuccess {networkState.postValue(NetworkState.Success()) }
            .doOnError { networkState.postValue(NetworkState.Error(it.message)) }
            .subscribe({

            }, {
                networkState.postValue(NetworkState.Error(it.message))
            }))*/

        /*currencyRepository.getRates(base)
            .doOnSubscribe {
                networkState.postValue(NetworkState.Loading())
            }
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .repeatUntil({ viewStopped || !base.equals(currentBase, ignoreCase = true) })
            .subscribe({

                networkState.postValue(NetworkState.Success(200))

                Log.e("","CurrencyUseCase checkRates base: ${it.base}")
                Log.e("","CurrencyUseCase checkRates base: ${it.rates}")

            }, {
                networkState.postValue(NetworkState.Error(400))
            }).addTo(disposable)*/

        //If the base didn't changed, we simply update the amount
        if (currency.equals(currentBase, ignoreCase = true)) {
            Log.e(TAG,"CurrencyUseCase checkRates currency if : $currency")
        } else {
            Log.e(TAG,"CurrencyUseCase checkRates currency else : $currency")
            currentBase = currency
            disposable.add(currencyRepository.getRates(currency)
                .doOnSubscribe {
                    networkState.postValue(NetworkState.Loading())
                }
                .delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .repeatUntil({ viewStopped || !currency.equals(currentBase, ignoreCase = true) })
                .subscribe({

                    networkState.postValue(NetworkState.Success(it))

                    Log.e(TAG,"CurrencyUseCase checkRates Success base: ${it.base}")
                    Log.e(TAG,"CurrencyUseCase checkRates Success rates: ${it.rates}")

                }, {
                    Log.e(TAG,"CurrencyUseCase checkRates Error: ${it.message}")
                    networkState.postValue(NetworkState.Error(it.message,null))
                }))
        }

        return disposable
    }

    fun getNetworkState(): MutableLiveData<NetworkState<CurrencyRateData>> = networkState
}