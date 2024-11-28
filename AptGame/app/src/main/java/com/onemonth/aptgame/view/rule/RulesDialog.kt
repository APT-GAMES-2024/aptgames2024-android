package com.onemonth.aptgame.view.rule

import android.os.Bundle
import android.view.View
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentRulesDialogBinding
import com.onemonth.aptgame.view.base.BaseDialogFragment
import com.onemonth.aptgame.view.questiondialog.QuestionDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RulesDialog : BaseDialogFragment<FragmentRulesDialogBinding>(R.layout.fragment_rules_dialog) {

    override fun getTheme(): Int = androidx.appcompat.R.style.AlertDialog_AppCompat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnListener()
    }

    private fun setOnListener() {
        binding.btnGameStart.setOnClickListener {
            dismiss()
            QuestionDialogFragment.newInstance().show(
                parentFragmentManager,
                "AnswerDialog"
            )
        }
    }
}