package com.ralph.gabb.projectpos.ui.main.shop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ralph.gabb.projectpos.data.db.Category
import com.ralph.gabb.projectpos.data.db.Product
import com.ralph.gabb.projectpos.data.local.ItemOrder


/*
 * Created by Ralph Gabrielle Orden on 9/3/20.
 */

class ShopViewModel: ViewModel() {

    private var allProducts = emptyList<Product>()
    var products =  arrayListOf<Product>()

    var isListUpdated =  MutableLiveData<Boolean>()
    var isItemOrderListUpdated = MutableLiveData<Boolean>()

    var totalAmount = MutableLiveData<Double>()
    val orderList =  OrderList()

    fun setProducts(products: List<Product>) {
         allProducts = products
        this.products = ArrayList(products)
    }

    fun filterProductByCategory(category: Category) {
        products.let {
            it.clear()
            return@let it.addAll(
                allProducts.filter { products ->
                    products.categoryId == category.id
                }
            )
        }

        notifyListChange()
    }

    private fun notifyListChange() {
        isListUpdated.value = true
    }

    private fun notifyItemOrderChange() {
        isItemOrderListUpdated.value = true
    }

    fun addOrder(itemOrder: ItemOrder) {
        orderList.addOrder(itemOrder)

        computeTotalAmount()
        notifyItemOrderChange()
    }

    private fun computeTotalAmount() {
        var total = 0.0

        for (itemOrder in orderList.orders) {
            total = (total + itemOrder.total)
        }

        totalAmount.value = total
    }

}