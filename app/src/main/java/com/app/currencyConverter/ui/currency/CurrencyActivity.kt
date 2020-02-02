package com.app.currencyConverter.ui.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.currencyConverter.R
import com.app.currencyConverter.commons.communicator.CurrencyItem
import com.app.currencyConverter.commons.util.Constants.Companion.AMOUNT_VALUE
import com.app.currencyConverter.commons.util.Constants.Companion.CURRENCY_VALUE
import com.app.currencyConverter.commons.util.Constants.Companion.EUR_BASE_PRICE
import com.app.currencyConverter.commons.util.Constants.Companion.EUR_BASE_VALUE
import com.app.currencyConverter.commons.util.Constants.Companion.IS_AMOUNT_UPDATED
import com.app.currencyConverter.commons.util.UiHelper
import com.app.currencyConverter.datasource.remote.NetworkState
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyActivity : AppCompatActivity(), CurrencyItem {

    private val currencyVM : CurrencyVM by viewModel()
    private val uiHelper : UiHelper by inject()
    private val currencyAdapter = CurrencyAdapter()
    private var isAmountUpdated : Boolean = false
    private var currency : String = EUR_BASE_VALUE
    private var amount : Double = EUR_BASE_PRICE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.getBoolean(IS_AMOUNT_UPDATED)?.let { isAmountUpdated = it }
        savedInstanceState?.getString(CURRENCY_VALUE)?.let { currency = it }
        savedInstanceState?.getDouble(AMOUNT_VALUE)?.let { amount = it }

        initRecyclerView()

        /*
         * Check Internet Connection
         * */

        if(uiHelper.getConnectivityStatus()) configureObservables()
        else uiHelper.showSnackBar(currency_rootView, resources.getString(R.string.error_message_network))
    }

    private fun configureObservables() {

        showProgressBar(true)
        currencyObserver(currency,amount,isAmountUpdated)

        /*
         * Progress Updater
         * */
        currencyVM.networkState.observe(this, Observer {

            it?.let {
                when(it) {
                    is NetworkState.Success -> {
                        showProgressBar(false)
                        it.let { it.data?.let { data -> currencyAdapter.update(data) } }
                    }
                    is NetworkState.Error ->  showProgressBar(false)
                }
            }
        })
    }

    private fun initRecyclerView() {
        /*
         * Setup the adapter class for the RecyclerView
         * */
        recylv_currency.layoutManager = LinearLayoutManager(this)
        recylv_currency.adapter = currencyAdapter
        currencyAdapter.setonCurrencyItemClickListener(this)
    }

    // UPDATE UI ----
    private fun showProgressBar(display : Boolean) {
        if(!display) progress_bar.visibility = View.GONE
        else progress_bar.visibility = View.VISIBLE
    }

    private fun currencyObserver(currency: String, amount: Double,isAmountUpdated : Boolean) {
        currencyVM.handleCurrencyRate(currency,amount,isAmountUpdated)
    }

    // Save data to Bundle when screen rotates
    override fun onSaveInstanceState(outState : Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(IS_AMOUNT_UPDATED,true)
        outState.putString(CURRENCY_VALUE,currency)
        outState.putDouble(AMOUNT_VALUE,amount)
    }

    // Restore data from Bundle when screen rotates
    override fun onRestoreInstanceState(savedInstanceState : Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getBoolean(IS_AMOUNT_UPDATED).let { isAmountUpdated = it }
        savedInstanceState.getString(CURRENCY_VALUE)?.let { currency = it }
        savedInstanceState.getDouble(AMOUNT_VALUE).let { amount = it }
    }

    override fun onAmountChanged(currency: String, amount: Double) {
        isAmountUpdated = true
        this.currency = currency
        this.amount = amount
        currencyObserver(currency,amount,isAmountUpdated)
    }
}
