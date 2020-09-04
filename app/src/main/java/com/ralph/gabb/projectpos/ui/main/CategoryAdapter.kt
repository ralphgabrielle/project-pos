package com.ralph.gabb.projectpos.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.data.db.Category
import kotlinx.android.synthetic.main.row_category.view.*


/*
 * Created by Ralph Gabrielle Orden on 8/3/20.
 */

class CategoryAdapter(
    private val context: Context, private val categories: List<Category>, private val onSelectCategory: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var currentSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]

        holder.displayItemName(category.name)
        holder.displayItemImageUrl(category.itemImageUrl, category.name)
        holder.checkIfSelected(position, currentSelected, context.getColor(R.color.card_color))

        holder.selectCategory {
            currentSelected = position
            notifyDataSetChanged()

            onSelectCategory(category)
        }
    }

    class CategoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        fun displayItemName(name: String) {
            itemView.tvCategoryName.text = name
        }

        fun displayItemImageUrl(url: String?, itemName: String) {
            if (url.isNullOrEmpty()) {
                //
                // Create text drawable here displaying initial of item name
                //
                val drawable = TextDrawable.builder()
                    .buildRoundRect(itemName.first().toString(), Color.DKGRAY, 16)

                itemView.ivCategoryImage.setImageDrawable(drawable)
                return
            }
        }

        fun selectCategory(onSelected: () -> Unit) {
            itemView.cardCategory.setOnClickListener {
                onSelected()
            }
        }

        fun checkIfSelected(position: Int, currentSelected: Int, selectedColor: Int) {
            if (position == currentSelected) {
                // selected
                itemView.cardCategory.setCardBackgroundColor(selectedColor)
            } else {
                // not selected
                itemView.cardCategory.setCardBackgroundColor(Color.WHITE)
            }
        }
    }

}