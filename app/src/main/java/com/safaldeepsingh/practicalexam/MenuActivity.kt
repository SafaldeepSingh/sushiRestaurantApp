package com.safaldeepsingh.practicalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.safaldeepsingh.practicalexam.db.CartTable
import com.safaldeepsingh.practicalexam.adapter.ProductsAdapter
import com.safaldeepsingh.practicalexam.db.ProductManager
import com.safaldeepsingh.practicalexam.entities.CartItem

class MenuActivity : AppCompatActivity() {
    val productsAdapter: ProductsAdapter = ProductsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        //find Views
        val productsRecyclerView:RecyclerView = findViewById(R.id.menu_products)
        val checkout: Button = findViewById(R.id.menu_checkout)

        productsRecyclerView.adapter = productsAdapter

        //init Cart
        val products = ProductManager.Products
        val cartItems = mutableListOf<CartItem>()
        products.forEach { product->
            cartItems.add(CartItem(product,0))
        }
        productsAdapter.setData(cartItems)

        //listeners
        checkout.setOnClickListener {
            val cartItems = productsAdapter.getCartData()
            if(cartItems.isNotEmpty()){
                //save quantity to DB
                saveCartToDB(cartItems)
                //open checkout Activity
                val openCheckOutActivity = Intent(this, CheckOutActivity::class.java)
                startActivity(openCheckOutActivity)
            }else{
                //Show Dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Cart Empty")
                builder.setMessage("Please Add Atleast One Product")
                builder.setPositiveButton("OK"){ _, _ ->}
                builder.show()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val cartTable = CartTable(this)
        cartTable.deleteAll()
    }
    private fun saveCartToDB(cartItems: List<CartItem>) {
        val cartTable= CartTable(this)
        for(cartItem in cartItems){
            cartTable.insert(cartItem)
        }
    }

}