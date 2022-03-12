package com.safaldeepsingh.practicalexam.db

import android.provider.BaseColumns

object SushiDbContract {
    object CartTable: BaseColumns{
        const val TABLE_NAME = "cart"
        const val PRODUCT_ID = "product_id"
        const val QUANTITY = "quantity"
    }
}