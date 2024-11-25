package com.onemonth.aptgame.view.dialog.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.DialogResultPointBinding
import kotlinx.coroutines.launch

class ResultPointDialog : DialogFragment() {

    private var onNextClick: (() -> Unit)? = null
    private var onShareClick: (() -> Unit)? = null

    private lateinit var binding: DialogResultPointBinding
    private val viewModel: ResultPointViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_result_point, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0f)

        setupClickListeners()
        observeStates()

        return binding.root
    }

    private fun setupClickListeners() {
        binding.apply {
            imageButtonClose.setOnClickListener {
                dismiss()
            }

            buttonShare.setOnClickListener {
                onShareClick?.invoke()
            }

            buttonNext.setOnClickListener {
                onNextClick?.invoke()
                dismiss()
            }
        }
    }

    private fun observeStates() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.reward.collect { reward ->
                    binding.apply {
                        textViewPoint.text = getString(R.string.format_points, reward.points)

                        reward.apartmentType?.let { type ->
                            imageViewApt.setImageResource(type.imageResId)
                            imageViewApt.visibility = View.VISIBLE
                        } ?: run {
                            imageViewApt.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "ResultPointDialog"

        fun show(
            fragmentManager: FragmentManager,
            onNextClick: () -> Unit,
            onShareClick: () -> Unit,
        ) {
            if (fragmentManager.findFragmentByTag(TAG) != null) return
            val dialog = ResultPointDialog().apply {
                this.onNextClick = onNextClick
                this.onShareClick = onShareClick
            }
            dialog.show(fragmentManager, TAG)
        }
    }
}
