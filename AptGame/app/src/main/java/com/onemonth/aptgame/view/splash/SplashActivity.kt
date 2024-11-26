package com.onemonth.aptgame.view.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.onemonth.aptgame.R
import com.onemonth.aptgame.databinding.ActivitySplashBinding
import com.onemonth.aptgame.util.extention.repeatOnStarted
import com.onemonth.aptgame.view.base.BaseActivity
import com.onemonth.aptgame.view.base.BaseViewModel
import com.onemonth.aptgame.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    private val splashViewModel: SplashViewModel by viewModels()
    private var deviceId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnDeviceId()
        isExistUserByCurrentDevice()
        setOnView()
        setOnObserver()
        lifecycleScope.launch {
            setOnRemoteConfig()
        }
    }

    @SuppressLint("HardwareIds")
    private fun setOnDeviceId() {
        deviceId = Settings.Secure.getString(
            binding.root.context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    private fun setOnView() {

    }

    private fun isExistUserByCurrentDevice() {
        splashViewModel.isExistUser(deviceId = deviceId ?: return)
    }

    private fun setOnObserver() {
        repeatOnStarted {
            splashViewModel.createUserFlow.collectLatest {
                //이후 로직 진행
                lifecycleScope.launch {
                    //테스트로 2초 후 메인으로 이동
                    delay(2000)
                    startActivity(Intent(binding.root.context, MainActivity::class.java))
                    finish()
                }
            }
        }

        repeatOnStarted {
            splashViewModel.isExistUserFlow.collectLatest { result ->

                //존재 = 생성 x 이후 로직 진행 / 존재 != 생성 o
                when (result) {
                    is BaseViewModel.Result.Success -> {
                        if (result.data) {
                            //이미 계정 있음
                            lifecycleScope.launch {
                                //테스트로 2초 후 메인으로 이동
                                delay(2000)
                                startActivity(Intent(binding.root.context, MainActivity::class.java))
                                finish()
                            }
                        } else {
                            //생성
                            createUser()
                        }
                    }

                    else -> {
                        //에러
                    }
                }
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun createUser() {
        splashViewModel.createUser(deviceId = deviceId ?: return)
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