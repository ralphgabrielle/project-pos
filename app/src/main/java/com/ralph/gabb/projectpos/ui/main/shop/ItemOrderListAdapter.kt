package com.ralph.gabb.projectpos.ui.main.shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.data.local.ItemOrder
import kotlinx.android.synthetic.main.row_item_order.view.*


/*
 * Created by Ralph Gabrielle Orden on 9/4/20.
 */

class ItemOrderListAdapter(
    private var context: Context, private var orderList: ArrayList<ItemOrder>, private var onSelectItemOrder: (ItemOrder) -> Unit
) : RecyclerView.Adapter<ItemOrderListAdapter.ItemOrderListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemOrderListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item_order, parent, false)
        return ItemOrderListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holderItem: ItemOrderListViewHolder, position: Int) {
        val itemOrder = orderList[position]

        holderItem.selectItemOrder {
            onSelectItemOrder(itemOrder)
        }
    }

    class ItemOrderListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun selectItemOrder(onSelectItemOrder: () -> Unit) {
            itemView.cardItemOrder.setOnClickListener {
                onSelectItemOrder()
            }
        }

    }

}