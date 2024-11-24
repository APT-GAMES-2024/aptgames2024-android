package com.onemonth.aptgame.view.rule

import android.os.Bundle
import android.view.View
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentRulesDialogBinding
import com.onemonth.aptgame.view.base.BaseDialogFragment

class RulesDialog : BaseDialogFragment<FragmentRulesDialogBinding>(R.layout.fragment_rules_dialog) {

    override fun getTheme(): Int = R.style.TransparentDialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}