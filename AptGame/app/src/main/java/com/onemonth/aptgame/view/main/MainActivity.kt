package com.onemonth.aptgame.view.main

import android.os.Bundle
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.ActivityMainBinding
import com.onemonth.aptgame.view.base.BaseActivity
import com.onemonth.aptgame.view.rule.RulesDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RulesDialog().show(this.supportFragmentManager, "tag")

    }
}