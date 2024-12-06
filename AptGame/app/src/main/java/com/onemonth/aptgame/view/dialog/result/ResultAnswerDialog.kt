package com.onemonth.aptgame.view.dialog.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.DialogResultAnswerBinding
import kotlinx.coroutines.launch

class ResultAnswerDialog : DialogFragment() {

    private var onConfirmClick: (() -> Unit)? = null

    private lateinit var binding: DialogResultAnswerBinding
    private val viewModel: ResultAnswerViewModel by viewModels()
    private val answerAdapter = AnswerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_result_answer, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0f)

        setupRecyclerView()
        setupClickListeners()
        observeStates()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerViewAnswers.apply {
            adapter = answerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupClickListeners() {
        binding.buttonConfirm.setOnClickListener {
            onConfirmClick?.invoke()
            dismiss()
        }
    }

    private fun observeStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.answerUiState.collect { state ->
                    when (state) {
                        is ResultAnswerUiState.Loading -> {
                            binding.progressBar.isVisible = true
                            binding.recyclerViewAnswers.isVisible = false
                        }
                        is ResultAnswerUiState.Success -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewAnswers.isVisible = true
                            answerAdapter.submitList(state.answers)
                        }
                        is ResultAnswerUiState.Error -> {
                            binding.progressBar.isVisible = false
                            binding.recyclerViewAnswers.isVisible = false
                            // TODO: 에러 핸들링
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "ResultAnswerDialog"

        fun show(
            fragmentManager: FragmentManager,
            onConfirmClick: () -> Unit,
        ) {
            if (fragmentManager.findFragmentByTag(TAG) != null) return
            val dialog = ResultAnswerDialog().apply {
                this.onConfirmClick = onConfirmClick
            }
            dialog.show(fragmentManager, TAG)
        }
    }
}
