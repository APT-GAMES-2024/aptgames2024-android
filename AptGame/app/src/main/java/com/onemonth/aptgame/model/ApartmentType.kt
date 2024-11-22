package com.onemonth.aptgame.model

import com.onemonth.aptgame.R

enum class ApartmentType(val id: Int, val imageResId: Int) {
    APT_1(1, R.drawable.image_apt_1),
    APT_2(2, R.drawable.image_apt_2),
    APT_3(3, R.drawable.image_apt_3),
    APT_4(4, R.drawable.image_apt_4),
    APT_5(5, R.drawable.image_apt_5),
    APT_6(6, R.drawable.image_apt_6),
    APT_7(7, R.drawable.image_apt_7),
    APT_8(8, R.drawable.image_apt_8),
    APT_9(9, R.drawable.image_apt_9),
    APT_10(10, R.drawable.image_apt_10),
    APT_11(11, R.drawable.image_apt_11),
    APT_12(12, R.drawable.image_apt_12),
    APT_13(13, R.drawable.image_apt_13),
    APT_14(14, R.drawable.image_apt_14),
    APT_15(15, R.drawable.image_apt_15),
    APT_16(16, R.drawable.image_apt_16),
    APT_17(17, R.drawable.image_apt_17),
    APT_18(18, R.drawable.image_apt_18),
    APT_19(19, R.drawable.image_apt_19),
    APT_20(20, R.drawable.image_apt_20),
    APT_21(21, R.drawable.image_apt_21),
    APT_22(22, R.drawable.image_apt_22),
    APT_23(23, R.drawable.image_apt_23);

    companion object {
        fun getTypeById(id: Int) = entries.find { it.id == id }
    }
}
