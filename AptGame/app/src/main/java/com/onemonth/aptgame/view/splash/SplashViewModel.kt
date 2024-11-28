package com.onemonth.aptgame.view.splash

import androidx.lifecycle.viewModelScope
import com.onemonth.aptgame.model.UserModel
import com.onemonth.aptgame.model.user.UserData
import com.onemonth.aptgame.repository.UserRepository
import com.onemonth.aptgame.util.flow.MutableEventFlow
import com.onemonth.aptgame.util.flow.asEventFlow
import com.onemonth.aptgame.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _createUserFlow = MutableEventFlow<Result<Boolean>>()
    val createUserFlow = _createUserFlow.asEventFlow()

    private val _isExistUserFlow = MutableEventFlow<Result<Boolean>>()
    val isExistUserFlow = _isExistUserFlow.asEventFlow()

    private val _getUserDataFlow = MutableEventFlow<UserModel>()
    val getUserDataFlow = _getUserDataFlow.asEventFlow()

    fun createUser(deviceId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.createUser(
                    user = UserModel(
                        deviceId = deviceId,
                        createAt = System.currentTimeMillis(),
                        point = 100
                    )
                )
            }.onSuccess { isDone ->
                _createUserFlow.emit(Result.Success(isDone))
            }.onFailure {
                _createUserFlow.emit(Result.Error(it))
            }
        }
    }

    fun isExistUser(deviceId: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                userRepository.isExistUserData(deviceId = deviceId)
            }.onSuccess { isExist ->
                _isExistUserFlow.emit(Result.Success(isExist))
            }.onFailure {
                _isExistUserFlow.emit(Result.Error(it))
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            runCatching {
                userRepository.getUserData(UserData.getUserData().deviceId ?: return@launch)
            }.onSuccess {
                _getUserDataFlow.emit(it)
            }.onFailure {
                _errorFlow.emit(BaseError(code = null, throwable = it, cause = null))
            }
        }
    }
}