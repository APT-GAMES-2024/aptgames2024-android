package com.onemonth.aptgame.view.questiondialog

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentQuestionDialogBinding


class QuestionDialogFragment : DialogFragment() {
    private var _binding: FragmentQuestionDialogBinding? = null
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
        _binding = FragmentQuestionDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
    }

    private fun setupButtons() {
        binding.button2.setOnClickListener {
            if (binding.innerTextfield.text.toString().isNotEmpty()) {
                shareContent()
            } else {
                Toast.makeText(requireContext(), "Please enter text", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun shareContent() {
        val questionText = binding.innerTextfield.text.toString()

        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
        intent.type = "text/plain"

        val appUrl = "https://apt.com/question"
        val content = "A new question has arrived!\n" +
                "Question: $questionText\n" +
                "Click here to check it out!"

        intent.putExtra(Intent.EXTRA_TEXT, "$content\n\n$appUrl")

        val chooserTitle = "Share with friends"
        startActivity(Intent.createChooser(intent, chooserTitle))
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = QuestionDialogFragment()
    }
}