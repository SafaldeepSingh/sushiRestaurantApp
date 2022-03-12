package com.safaldeepsingh.practicalexam.entities

import androidx.annotation.DrawableRes

class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    @DrawableRes val image: Int,
) {
}