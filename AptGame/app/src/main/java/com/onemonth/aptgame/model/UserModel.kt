package com.onemonth.aptgame.model

data class UserModel(
    val deviceId: String? = "",
    val createAt: Long? = null,
    val point: Long? = null,
    val aptCards: ArrayList<String>? = null, //apt Card Id
)
