package com.onemonth.aptgame.view.rule

import android.os.Bundle
import android.view.View
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentRulesDialogBinding
import com.onemonth.aptgame.view.base.BaseDialogFragment
import com.onemonth.aptgame.view.questiondialog.AnswerDialogFragment
import com.onemonth.aptgame.view.questiondialog.QuestionDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RulesDialog : BaseDialogFragment<FragmentRulesDialogBinding>(R.layout.fragment_rules_dialog) {
    private var sharedQuestionText: String? = null

    override fun getTheme(): Int = androidx.appcompat.R.style.AlertDialog_AppCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 공유된 텍스트 받기
        sharedQuestionText = arguments?.getString(ARG_SHARED_QUESTION)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnListener()
    }

    private fun setOnListener() {
        binding.btnGameStart.setOnClickListener {
            dismiss()
            if (sharedQuestionText != null) {
                // 공유 링크로 들어온 경우
                AnswerDialogFragment.newInstance(sharedQuestionText!!).show(
                    parentFragmentManager,
                    "AnswerDialog"
                )
            } else {
                // 일반적으로 접근한 경우
                QuestionDialogFragment.newInstance().show(
                    parentFragmentManager,
                    "QuestionDialog"
                )
            }
            QuestionDialogFragment.newInstance().show(
                parentFragmentManager,
                "AnswerDialog"
            )
        }
    }

    companion object {
        private const val ARG_SHARED_QUESTION = "arg_shared_question"

        fun newInstance(sharedQuestion: String? = null): RulesDialog {
            return RulesDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_SHARED_QUESTION, sharedQuestion)
                }
            }
        }
    }
}
