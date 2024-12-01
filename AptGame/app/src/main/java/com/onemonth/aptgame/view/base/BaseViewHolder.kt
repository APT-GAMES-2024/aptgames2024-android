package com.onemonth.aptgame.view.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

//멀티뷰에 사용
abstract class BaseViewHolder<out T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val binding: T = DataBindingUtil.bind(view)!!

    open fun onBind(position: Int) {
        itemView.tag = position
    }

    open fun onBind(position: Int, payloads: MutableList<Any>? = null) {
        itemView.tag = position
    }
}