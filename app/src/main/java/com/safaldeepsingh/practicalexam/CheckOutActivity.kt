package com.safaldeepsingh.practicalexam

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.safaldeepsingh.practicalexam.db.CartTable
import com.safaldeepsingh.practicalexam.adapter.ProductsAdapter
import java.text.NumberFormat

class CheckOutActivity : AppCompatActivity() {
    val productsAdapter = ProductsAdapter(true)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        //find Views
        val purchase: Button = findViewById(R.id.checkout_purchase)
        val productsRecyclerView: RecyclerView = findViewById(R.id.checkout_products)
        val totalPriceTextView: TextView = findViewById(R.id.checkout_total)

        productsRecyclerView.adapter = productsAdapter

        //get CartItems
        val cartTable = CartTable(this)
        val cartItems = cartTable.getAll()
        var totalPrice = 0.0
        cartItems.forEach { cartItem->
            totalPrice+=cartItem.quantity*cartItem.product.price
        }
        totalPriceTextView.text = formatMoney(totalPrice)
        productsAdapter.setData(cartItems)

        //listeners
        purchase.setOnClickListener {
            //show alert Dialog
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Confirm Purchase")
            builder.setNegativeButton("Modify",this::onDialogButtonClick)
            builder.setPositiveButton("Accept",this::onDialogButtonClick)
            builder.setNeutralButton("Cancel",this::onDialogButtonClick)
            builder.show()
        }
    }
    private fun onDialogButtonClick(dialog: DialogInterface, position: Int){
        when(position){
            Dialog.BUTTON_POSITIVE->{
                val cartTable = CartTable(this)
                cartTable.deleteAll()
                val openMain = Intent(this, MainActivity::class.java)
                startActivity(openMain)
                finish()
            }
            Dialog.BUTTON_NEUTRAL->{
            }
            Dialog.BUTTON_NEGATIVE->{
                finish()

            }
        }
    }
    private fun formatMoney(price: Double): String {
        val formatter = NumberFormat.getCurrencyInstance()
        return formatter.format(price)

    }
}