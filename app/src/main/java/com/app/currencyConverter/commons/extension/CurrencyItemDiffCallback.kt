package com.app.currencyConverter.commons.extension

import androidx.recyclerview.widget.DiffUtil
import com.app.currencyConverter.domain.entities.RateList

class CurrencyItemDiffCallback(private var oldList: ArrayList<RateList>,
                               private var newList: ArrayList<RateList>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = true

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition] == newList[newItemPosition]
}