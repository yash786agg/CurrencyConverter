package com.app.currencyConverter.ui.currency

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.currencyConverter.R
import com.app.currencyConverter.commons.communicator.CurrencyItem
import com.app.currencyConverter.commons.extension.CurrencyItemDiffCallback
import com.app.currencyConverter.commons.extension.moveCursorToProperPosition
import com.app.currencyConverter.commons.util.CurrencyUtil.Companion.rateStringToDouble
import com.app.currencyConverter.databinding.AdapterCurrencyListBinding
import com.app.currencyConverter.domain.entities.RateList

class CurrencyAdapter: RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    private var onCurrencyItemClickListener : CurrencyItem? = null
    private var rateList : ArrayList<RateList> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: AdapterCurrencyListBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.adapter_currency_list, parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
            = holder.bind(rateList[position],onCurrencyItemClickListener)

    // Gets the number of Items in the list
    override fun getItemCount(): Int = rateList.size

    fun setonCurrencyItemClickListener(onCurrencyItemClickListener : CurrencyItem) {
        this.onCurrencyItemClickListener = onCurrencyItemClickListener
    }

    fun update(list: ArrayList<RateList>) {
        val result = DiffUtil.calculateDiff(CurrencyItemDiffCallback(rateList, list), false)
        rateList = list
        result.dispatchUpdatesTo(this)
    }

    inner class MyViewHolder(private val binding: AdapterCurrencyListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(rate: RateList,clickListener: CurrencyItem?) {
            binding.rateList = rate
            binding.rateItemLayout.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) scrollToTop()
            }
            binding.currencyRateEdtxt.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) scrollToTop()
            }

            binding.currencyRateEdtxt.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    val inputAmount = s.toString()
                    binding.currencyRateEdtxt.moveCursorToProperPosition()

                    if (binding.currencyRateEdtxt.isFocused && inputAmount != String.format("%,.2f", rate.rate)) {

                        val baseCurrencyRate = if (inputAmount.isEmpty() && inputAmount == ".") 1.0
                        else rateStringToDouble(inputAmount)

                        clickListener?.onAmountChanged(rateList[0].currency, baseCurrencyRate)
                    }
                }
            })
        }

        private fun scrollToTop() {

            //If view is already on top, we do nothing, otherwise...
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->

                //We move it from its current position
                rateList.removeAt(currentPosition).also {
                    //And we add it to the top
                    rateList.add(0, it)
                    onCurrencyItemClickListener?.onAmountChanged(rateList[0].currency,rateList[0].rate)
                }

                //We notify the recyclerview the view moved to position 0
                notifyItemMoved(currentPosition, 0)
            }
        }
    }
}