package com.onemonth.aptgame.view.main

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.ActivityMainBinding
import com.onemonth.aptgame.util.extention.setOnSingleClickListener
import com.onemonth.aptgame.view.base.BaseActivity
import com.onemonth.aptgame.view.rule.RulesDialog
import com.onemonth.aptgame.view.shop.ShopDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    lateinit var rulesDialog: RulesDialog
    lateinit var shopDialog: ShopDialogFragment
    private var lastShowDialog: DialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rulesDialog = RulesDialog()

        shopDialog = ShopDialogFragment(dismissCallback = {
            resumeShowLastDialog()
        })

        rulesDialog.show(this.supportFragmentManager, "tag")
        setOnView()
        setOnListener()
    }

    private fun setOnView() {
        Glide.with(binding.root.context).load(R.drawable.ic_system_fire)
            .circleCrop()
            .override(24,24)
            .into(binding.ivFire)
    }

    private fun resumeShowLastDialog() {
        lastShowDialog?.show(this.supportFragmentManager, "tag")
    }

    private fun setOnListener() {
        binding.clShop.setOnClickListener {
            println("테스트입니다 1")
            rulesDialog.dismiss()
            shopDialog.show(this.supportFragmentManager, "tag")
        }
        binding.ivFire.setOnSingleClickListener {
            println("테스트입니다 2")
        }
        binding.main.setOnClickListener {
            println("테스트입니다 3")
        }
    }
}