package com.ralph.gabb.projectpos.ui.main

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ralph.gabb.projectpos.R
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.row_navigation.view.*

/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

class NavigationAdapter(private var context: Context, private var onSelectNavigation: (Navigation) -> Unit): RecyclerView.Adapter<NavigationAdapter.NavigationViewHolder>() {

    private var currentSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_navigation, parent, false)
        return NavigationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Navigation.values().size
    }

    override fun onBindViewHolder(holder: NavigationViewHolder, position: Int) {
        val navigation = Navigation.values()[position]
        holder.displayNavigation(navigation)
        holder.checkIfSelected(position, currentSelected)

        holder.selectNavigation {
            onSelectNavigation(navigation)

            currentSelected = position
            notifyDataSetChanged()
        }
    }

    class NavigationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun displayNavigation(navigation: Navigation) {
            itemView.tvNavigation.text = navigation.name
            itemView.navigationIcon.setImageResource(navigation.icon)
        }

        fun selectNavigation(selectNavigation: () -> Unit) {
            itemView.containerNavigation.setOnClickListener {
                selectNavigation()
            }
        }

        fun checkIfSelected(position: Int, currentSelected: Int) {
            if (position == currentSelected) {
                itemView.containerNavigation.setBackgroundResource(R.drawable.theme_rounded_primary)
                itemView.navigationIcon.setColorFilter(Color.WHITE, android.graphics.PorterDuff.Mode.MULTIPLY)
                itemView.tvNavigation.setTextColor(Color.WHITE)
            } else {
                itemView.containerNavigation.setBackgroundResource(0)
                itemView.navigationIcon.setColorFilter(Color.LTGRAY, android.graphics.PorterDuff.Mode.MULTIPLY)
                itemView.tvNavigation.setTextColor(Color.LTGRAY)
            }
        }
    }

}