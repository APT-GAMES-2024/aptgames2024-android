package com.onemonth.aptgame.view.base

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.onemonth.aptgame.R
import com.onemonth.aptgame.util.extention.dpToPx

abstract class BaseDialogFragment<B : ViewDataBinding>(private val layoutId: Int) :
    DialogFragment() {
    lateinit var binding: B
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = requireActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        setBackground()
        return binding.root
    }

    open fun setCancel(setting: Boolean) {
        isCancelable = setting
    }

    open fun setWindowFeature(feature: Int) {
        activity.requestWindowFeature(feature)
    }

    open fun setBackground() {

    }
}