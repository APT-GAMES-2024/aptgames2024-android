package com.onemonth.aptgame.model.user

import com.onemonth.aptgame.model.UserModel


object UserData {
    private var user = UserModel()

    @JvmStatic
    fun setUserData(newUser: UserModel) {
        user = newUser
    }

    @JvmStatic
    fun getUserData(): UserModel = user
}