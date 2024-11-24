package com.onemonth.aptgame.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.ActivitySplashBinding
import com.onemonth.aptgame.view.base.BaseActivity
import com.onemonth.aptgame.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnView()
        lifecycleScope.launch {
            setOnRemoteConfig()
        }
    }

    private fun setOnView() {
        lifecycleScope.launch {
            //테스트로 3초 후 메인으로 이동
            delay(3000)
            startActivity(Intent(binding.root.context, MainActivity::class.java))
            finish()
        }
    }

    private suspend fun setOnRemoteConfig() = suspendCancellableCoroutine { continuation ->
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var aosMinVersion = remoteConfig.getString("aos_possible_version")
                    var serverState = remoteConfig.getString("server_state")
                    continuation.resume(true)
                }
            }
            .addOnFailureListener { continuation.resume(false) }
    }
}