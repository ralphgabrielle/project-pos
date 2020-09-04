package com.ralph.gabb.projectpos.ui.main.shop

import com.ralph.gabb.projectpos.data.local.ItemOrder


/*
 * Created by Ralph Gabrielle Orden on 9/4/20.
 */

class OrderList {

    val orders = arrayListOf<ItemOrder>()

    fun addOrder(itemOrder: ItemOrder) {
        orders.add(itemOrder)
    }

    fun removeOrder(itemOrder: ItemOrder) {
        orders.remove(itemOrder)
    }

    fun removeAll() {
        orders.clear()
    }

}