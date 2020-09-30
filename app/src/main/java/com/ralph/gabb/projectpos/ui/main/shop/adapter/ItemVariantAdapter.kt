package com.ralph.gabb.projectpos.ui.main.shop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.data.db.ProductVariant
import kotlinx.android.synthetic.main.row_variants.view.*


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

class ItemVariantAdapter(private var context: Context,
                         private var variants: ArrayList<ProductVariant>,
                         private var onSelectVariant: (ProductVariant) -> Unit
): RecyclerView.Adapter<ItemVariantAdapter.ItemVariantViewHolder>() {

    private var currentSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVariantViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_variants, parent, false)
        return ItemVariantViewHolder(view)
    }

    override fun getItemCount() = variants.size

    override fun onBindViewHolder(holderVariant: ItemVariantViewHolder, position: Int) {
        val variant = variants[position]

        holderVariant.displayItemVariant(variant.name)
        holderVariant.checkIfSelected(position, currentSelected)

        holderVariant.selectVariant {
            currentSelected = position
            notifyDataSetChanged()

            onSelectVariant(variant)
        }
    }

    class ItemVariantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun displayItemVariant(variant: String) {
            itemView.tvProductVariant.text = variant
        }

        fun checkIfSelected(position: Int, currentSelected: Int) {
            if (position == currentSelected) {
                // selected
                itemView.tvProductVariant.setBackgroundResource(R.drawable.theme_rounded_neutral_selected)
            } else {
                // not selected
                itemView.tvProductVariant.setBackgroundResource(R.drawable.theme_rounded_neutral)
            }
        }

        fun selectVariant(selectVariant: () -> Unit) {
            itemView.tvProductVariant.setOnClickListener {
                selectVariant()
            }
        }

    }

}