package com.onemonth.aptgame.view.shop

import android.os.Bundle
import android.view.View
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentShopDialogBinding
import com.onemonth.aptgame.util.extention.disableItemAnimator
import com.onemonth.aptgame.util.extention.setOnSingleClickListener
import com.onemonth.aptgame.view.adapter.ShopItemAdapter
import com.onemonth.aptgame.view.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopDialogFragment(val dismissCallback: () -> Unit) :
    BaseDialogFragment<FragmentShopDialogBinding>(R.layout.fragment_shop_dialog) {

    override fun getTheme(): Int = R.style.DimDialogTheme

    private val shopItemAdapter by lazy {
        ShopItemAdapter(callback = { searchItem ->

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnView()
        setOnListener()
    }

    private fun setOnView() {
        binding.rvList.apply {
            adapter = shopItemAdapter
            disableItemAnimator()
        }
    }

    private fun setOnListener() {
        binding.btnBack.setOnSingleClickListener {
            dismiss()
            dismissCallback.invoke()
        }
    }
}