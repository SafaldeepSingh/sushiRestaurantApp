package com.safaldeepsingh.practicalexam.adapter

import android.view.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.safaldeepsingh.practicalexam.R
import com.safaldeepsingh.practicalexam.entities.CartItem
import java.text.NumberFormat

//import com.squareup.picasso.Picasso

class ProductsAdapter(val checkout: Boolean = false) : RecyclerView.Adapter<ProductsAdapter.SushiItemViewHolder>() {
//    private val dataSet = mutableListOf<Product>()
    private val cartItems = mutableListOf<CartItem>()
    class SushiItemViewHolder(private val parentAdapter: ProductsAdapter, private val containerView: View) : RecyclerView.ViewHolder(containerView) {
//        var product: Product? = null
        lateinit var cartItem: CartItem
        val image: ImageView = containerView.findViewById(R.id.productList_image)
        val title: TextView = containerView.findViewById(R.id.productList_title)
        val description: TextView = containerView.findViewById(R.id.productList_description)
        val price: TextView = containerView.findViewById(R.id.productList_price)
        val quantity: TextView = containerView.findViewById(R.id.productList_quantity)
        //view not available in both layouts
        val increaseQuantity:Button? = containerView.findViewById(R.id.productList_increaseQuantity)
        val decreaseQuantity:Button? = containerView.findViewById(R.id.productList_decreaseQuantity)
        val totalPrice: TextView?  = containerView.findViewById(R.id.productList_totalPrice)
            init {
            increaseQuantity?.setOnClickListener {
                cartItem.quantity++
                quantity.text = cartItem.quantity.toString()
            }
            decreaseQuantity?.setOnClickListener {
                if(cartItem.quantity>0){
                    cartItem.quantity--
                    quantity.text = cartItem.quantity.toString()
                }
            }

        }
    }

    fun getCartData() = this.cartItems.filter { it.quantity>0 }
    fun setData(dataSet: List<CartItem>) {
        this.cartItems.clear()
        this.cartItems.addAll(dataSet)
//        this.dataSet.clear()
//        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

//    fun addData(Product: Product){
//        dataSet.add(Product)
//        notifyDataSetChanged()
//    }

//    public fun removeData(Product: Product){
//        dataSet.remove(Product)
//        notifyDataSetChanged()
//    }

    //inflate the customView
    override fun onCreateViewHolder(parent: ViewGroup, customViewType: Int): SushiItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(customViewType,parent, false) //false because the recycler will add to the view hierarchy when it is time
        return SushiItemViewHolder(this, view)
    }

    //Called by the layoutManager to replace the content(data) of the CustomView
    override fun onBindViewHolder(holder: SushiItemViewHolder, positionInDataSet: Int) {
        val currentData = cartItems[positionInDataSet]
        holder.cartItem = currentData
        val product = currentData.product
        holder.image.setImageResource(product.image)
        holder.title.text = product.title
        holder.description.text = product.description
        holder.price.text = formatMoney(product.price)
        holder.quantity.text = currentData.quantity.toString()
        val price = currentData.quantity * currentData.product.price
        holder.totalPrice?.text = formatMoney(price)
    }

    override fun getItemCount() = cartItems.size
    override fun getItemViewType(position: Int): Int {
        if(checkout)
            return R.layout.product_list1
        return R.layout.product_list
    }
    private fun formatMoney(price: Double): String {
        val formatter = NumberFormat.getCurrencyInstance()
        return formatter.format(price)

    }
}











