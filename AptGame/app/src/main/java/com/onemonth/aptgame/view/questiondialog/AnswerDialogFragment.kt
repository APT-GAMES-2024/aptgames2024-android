package com.onemonth.aptgame.view.questiondialog

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentAnswerDialogBinding

class AnswerDialogFragment : DialogFragment() {
    private var _binding: FragmentAnswerDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.Theme_AptGame)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnswerDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupShareButton()
        setupCopyButton()
    }

    private fun setupCopyButton() {
        binding.button1.setOnClickListener {
            copyToClipboard()
        }
    }

    private fun copyToClipboard() {
        val question = binding.root.findViewById<TextView>(R.id.inner_card)
            .findViewById<TextView>(android.R.id.text1).text.toString()
        val answer = binding.textbox.text.toString()

        val clipboard = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val contentText = "질문: $question\n답변: $answer"
        val clip = ClipData.newPlainText("Shared Content", contentText)
        clipboard.setPrimaryClip(clip)

        Toast.makeText(
            requireContext(),
            "클립보드에 복사되었습니다",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupShareButton() {
        binding.squareButton2.setOnClickListener {
            shareContent()
        }
    }

    private fun shareContent() {
        val question = binding.root.findViewById<TextView>(R.id.inner_card)
            .findViewById<TextView>(android.R.id.text1).text.toString()
        val answer = binding.textbox.text.toString()

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            val contentText = "질문: $question\n답변: $answer"
            putExtra(Intent.EXTRA_TEXT, contentText)
        }

        val shareTitle = "친구에게 공유하기"
        startActivity(Intent.createChooser(shareIntent, shareTitle))
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = AnswerDialogFragment()
    }
}