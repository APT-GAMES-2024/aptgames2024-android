package com.onemonth.aptgame.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.ActivityMainBinding
import com.onemonth.aptgame.view.base.BaseActivity
import com.onemonth.aptgame.view.rule.RulesDialog
import com.onemonth.aptgame.view.shop.ShopDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    lateinit var shopDialog: ShopDialogFragment
    private var lastShowDialog: DialogFragment? = null
    private var currentShowDialog: DialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopDialog = ShopDialogFragment(dismissCallback = {
            resumeShowLastDialog()
        })

        // 공유 인텐트 처리
        if (intent?.action == Intent.ACTION_SEND) {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            val questionText = extractQuestionFromSharedText(sharedText)

            // 공유된 질문이 있는 경우 RulesDialog에 전달
            showFragment(RulesDialog.newInstance(questionText))
        } else {
            // 일반적인 앱 실행
            showFragment(RulesDialog.newInstance())
        }

        setOnView()
        setOnListener()
        backPressedDispatcher()
    }

    private fun backPressedDispatcher() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //팝업을 끄는 대신 이전 팝업 보여주기
                showFragment(lastShowDialog ?: return)
            }
        })
    }

    private fun extractQuestionFromSharedText(sharedText: String?): String? {
        return sharedText?.let {
            val pattern = "Question:\\s*(.+)\\nClick".toRegex()
            pattern.find(it)?.groupValues?.get(1)
        }
    }

    private fun setOnView() {
        Glide.with(binding.root.context).load(R.drawable.ic_system_fire)
            .circleCrop()
            .override(24, 24)
            .into(binding.ivFire)
    }

    private fun showFragment(dialog: DialogFragment) {
        if (currentShowDialog != null)
            currentShowDialog?.dismiss()
        if (!dialog.isAdded) {
            lastShowDialog = currentShowDialog
            currentShowDialog = dialog
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragment, dialog, "tag")
                .addToBackStack(null)
                .commit()
        }
    }

    private fun resumeShowLastDialog() {
        currentShowDialog?.dismiss()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment, lastShowDialog ?: return, "tag")
            .addToBackStack(null)
            .commit()
        val lastDialog = lastShowDialog
        lastShowDialog = currentShowDialog
        currentShowDialog = lastDialog
    }

    private fun setOnListener() {
        binding.clShop.setOnClickListener {
            if (!shopDialog.isAdded) {
                showFragment(shopDialog)
            }
        }
    }

    // Activity 재시작 시 인텐트 처리를 위한 메소드
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        // 새로운 공유 인텐트 처리
        if (intent?.action == Intent.ACTION_SEND) {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            val questionText = extractQuestionFromSharedText(sharedText)

            showFragment(RulesDialog.newInstance(questionText))
        }
    }
}

