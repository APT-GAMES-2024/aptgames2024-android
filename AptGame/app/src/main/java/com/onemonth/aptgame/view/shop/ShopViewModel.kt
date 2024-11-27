package com.onemonth.aptgame.view.shop

import com.onemonth.aptgame.R
import com.onemonth.aptgame.model.ShopModel
import com.onemonth.aptgame.repository.UserRepository
import com.onemonth.aptgame.view.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {


    fun getShopData(): List<ShopModel> {
        return listOf(
            ShopModel(cost = 100, aptCardId = "RUXURY.0001", aptCardImage = R.drawable.image_apt_1, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0002", aptCardImage = R.drawable.image_apt_2, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0003", aptCardImage = R.drawable.image_apt_3, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0004", aptCardImage = R.drawable.image_apt_4, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0005", aptCardImage = R.drawable.image_apt_5, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0006", aptCardImage = R.drawable.image_apt_6, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0007", aptCardImage = R.drawable.image_apt_7, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0008", aptCardImage = R.drawable.image_apt_8, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "RUXURY.0009", aptCardImage = R.drawable.image_apt_9, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "DESTORY.0001", aptCardImage = R.drawable.image_apt_10, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "DESTORY.0002", aptCardImage = R.drawable.image_apt_11, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "DESTORY.0003", aptCardImage = R.drawable.image_apt_12, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "DESTORY.0004", aptCardImage = R.drawable.image_apt_13, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "DESTORY.0005", aptCardImage = R.drawable.image_apt_14, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "FIRE.0001", aptCardImage = R.drawable.image_apt_15, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "ULTRA.RUXURY.0001", aptCardImage = R.drawable.image_apt_16, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "ULTRA.RUXURY.0002", aptCardImage = R.drawable.image_apt_17, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "ULTRA.RUXURY.0003", aptCardImage = R.drawable.image_apt_18, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "ULTRA.RUXURY.0004", aptCardImage = R.drawable.image_apt_19, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "COOKIE.0001", aptCardImage = R.drawable.image_apt_20, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "COOKIE.0002", aptCardImage = R.drawable.image_apt_21, cardName = "", cardVersion = "1.0.0",
                      cardDescription = ""),
            ShopModel(cost = 100, aptCardId = "COOKIE.0003", aptCardImage = R.drawable.image_apt_22, cardName = "", cardVersion = "1.0.0",
                      cardDescription = "")
        )
    }
}