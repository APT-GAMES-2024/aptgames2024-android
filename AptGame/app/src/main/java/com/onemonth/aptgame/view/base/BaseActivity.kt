package com.onemonth.aptgame.view.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.onemonth.aptgame.service.MusicService


abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes val layoutId: Int) :
    AppCompatActivity() {

    lateinit var binding: B
    lateinit var rootContext: Context
    lateinit var musicServiceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        rootContext = binding.root.context
        musicServiceIntent = Intent(this, MusicService::class.java)
    }

    override fun onStart() {
        super.onStart()
        startService(musicServiceIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(musicServiceIntent)
    }

    override fun onStop() {
        super.onStop()
        stopService(musicServiceIntent)
    }

    override fun onPause() {
        super.onPause()
        stopService(musicServiceIntent)
    }
}
