package com.onemonth.aptgame.model

data class ShopModel(
    val cost: Int = 0,
    val aptCardId: String = "",
    val aptCardImage: Int = 0,
    val cardName: String = "",
    val cardDescription: String = "",
    val cardVersion: String = "1.0.0"
)