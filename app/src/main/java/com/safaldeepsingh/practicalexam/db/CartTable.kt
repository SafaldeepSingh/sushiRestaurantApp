package com.safaldeepsingh.practicalexam.db

import android.content.ContentValues
import android.content.Context
import com.safaldeepsingh.practicalexam.entities.CartItem

class CartTable(context: Context) {
    private var dbHelper= SushiDbHelper(context)

    fun insert(cartItem: CartItem): Long{
        val values = ContentValues().apply {
            put(SushiDbContract.CartTable.PRODUCT_ID, cartItem.product.id)
            put(SushiDbContract.CartTable.QUANTITY, cartItem.quantity)
        }
        val writeToDb = dbHelper.writableDatabase
        val newRowId = writeToDb.insert(SushiDbContract.CartTable.TABLE_NAME, null, values)
        return newRowId
    }
    fun getAll(): List<CartItem>{
        val cartItems = mutableListOf<CartItem>()
        val readFromDb = dbHelper.readableDatabase
        val projection = arrayOf(
            SushiDbContract.CartTable.PRODUCT_ID,
            SushiDbContract.CartTable.QUANTITY
        )
        val orderBy = "${SushiDbContract.CartTable.QUANTITY} DESC"

        val cursor = readFromDb.query(
            SushiDbContract.CartTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            orderBy
        )
        val products = ProductManager.Products
        //cursor starts at -1
        with(cursor){
            while(moveToNext()){
                val productId = getInt(getColumnIndexOrThrow(SushiDbContract.CartTable.PRODUCT_ID))
                val product = products.filter { it.id == productId }[0]
                val cartItem = CartItem(
                    product,
                    getInt(getColumnIndexOrThrow(SushiDbContract.CartTable.QUANTITY))
                )
                cartItems.add(cartItem)
            }
        }
        cursor.close()
        return cartItems
    }
    fun deleteAll(): Boolean{
        val dbWrite = dbHelper.writableDatabase
        val deletedRows = dbWrite.delete(
            SushiDbContract.CartTable.TABLE_NAME,
            null,
            null
        )
        return deletedRows >= 1
    }
}