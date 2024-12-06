package com.onemonth.aptgame.view.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    lateinit var binding: B
    lateinit var rootContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        rootContext = binding.root.context
    }
}
