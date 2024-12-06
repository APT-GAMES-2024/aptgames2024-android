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
import androidx.lifecycle.lifecycleScope
import com.google.ai.client.generativeai.GenerativeModel
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.FragmentQuestionDialogBinding
import kotlinx.coroutines.launch


class QuestionDialogFragment : DialogFragment() {
    private var _binding: FragmentQuestionDialogBinding? = null
    private val binding get() = _binding!!

    private val geminiapiKey = "AIzaSyApjw_g8M36nyeFVssG41oAExOt93GYTRo" // Gemini API Key
    private val generativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-pro",
            apiKey = geminiapiKey
        )
    }
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
        binding.button1.setOnClickListener {
            generateVsTopic()
        }

        binding.button2.setOnClickListener {
            if (binding.innerTextfield.text.toString().isNotEmpty()) {
                shareContent()
            } else {
                Toast.makeText(requireContext(), "Please enter text", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun generateVsTopic() {
        binding.button1.isEnabled = false
        binding.innerTextfield.setText("Generating...")

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val prompt =
                    "Generate one interesting VS topic for discussion. Format: 'A VS B'. Example: 'Morning Person VS Night Person'"
                val response = generativeModel.generateContent(prompt)

                response.text?.let { result ->
                    binding.innerTextfield.setText(result)
                } ?: run {
                    throw Exception("Response is empty")
                }
            } catch (e: Exception) {
                binding.innerTextfield.setText("")
                Toast.makeText(
                    requireContext(),
                    "An error occurred: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } finally {
                binding.button1.isEnabled = true
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