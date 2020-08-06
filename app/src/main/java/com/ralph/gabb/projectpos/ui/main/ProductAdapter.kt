package com.ralph.gabb.projectpos.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.data.db.Product
import kotlinx.android.synthetic.main.row_category.view.*
import kotlinx.android.synthetic.main.row_product.view.*


/*
 * Created by Ralph Gabrielle Orden on 8/6/20.
 */

class ProductAdapter(private val context: Context, private val products: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        holder.displayProductName(product.productName)
        holder.displayProductPrice(product.productPrice)
        holder.displayProductImage(product.imageUrl, product.productName)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun displayProductName(itemName: String) {
            itemView.tvProductName.text = itemName
        }

        fun displayProductPrice(price: String?) {

        }

        fun displayProductImage(imageUrl: String?, itemName: String) {
            if (imageUrl.isNullOrEmpty()) {
                //
                // Create text drawable here displaying initial of item name
                //
                val drawable = TextDrawable.builder()
                    .buildRound(itemName.first().toString(), Color.DKGRAY)

                itemView.ivProductImage.setImageDrawable(drawable)
                return
            }
        }
    }
}