package com.onemonth.aptgame.view.main

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        shopDialog = ShopDialogFragment(dismissCallback = {
            resumeShowLastDialog()
        })

        //rules로 첫 화면 초기화
        showFragment(RulesDialog())

        setOnView()
        setOnListener()
    }

    private fun setOnView() {
        Glide.with(binding.root.context).load(R.drawable.ic_system_fire)
            .circleCrop()
            .override(24, 24)
            .into(binding.ivFire)
    }

    private fun showFragment(dialog: DialogFragment) {
        lastShowDialog?.dismiss()
        if (!dialog.isAdded) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragment, dialog, "tag")
                .addToBackStack(null)
                .commit()
            lastShowDialog = dialog
        }
    }

    private fun resumeShowLastDialog() {
        lastShowDialog?.show(this.supportFragmentManager, "tag")
    }

    private fun setOnListener() {
        binding.clShop.setOnClickListener {
            if (!shopDialog.isAdded) {
                showFragment(shopDialog)
            }
        }
    }
}