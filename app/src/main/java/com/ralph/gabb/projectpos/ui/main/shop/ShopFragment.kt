package com.ralph.gabb.projectpos.ui.main.shop

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseFragment
import com.ralph.gabb.projectpos.data.db.Category
import com.ralph.gabb.projectpos.data.db.Product
import com.ralph.gabb.projectpos.data.local.ItemOrder
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import com.ralph.gabb.projectpos.extra.emptyString
import com.ralph.gabb.projectpos.extra.plantLog
import com.ralph.gabb.projectpos.ui.main.CategoryAdapter
import com.ralph.gabb.projectpos.ui.main.ProductAdapter
import com.ralph.gabb.projectpos.utils.PreferenceManager
import kotlinx.android.synthetic.main.fragment_shop.*
import org.koin.android.ext.android.inject


/*
 * Created by Ralph Gabrielle Orden on 8/3/20.
 */

class ShopFragment: BaseFragment() {

    private val preferenceManager: PreferenceManager by inject()
    private val viewModel: ShopViewModel by inject()

    override val layoutId: Int?
        get() = R.layout.fragment_shop

    override fun viewCreated() {
        initCategories()
        initProducts()
        initItemOrderList()

        observeEvents()
    }

    private fun observeEvents() {
        viewModel.isListUpdated.observe(this, Observer {
            it ?: return@Observer

            rvProducts.adapter?.notifyDataSetChanged()
        })

        viewModel.isItemOrderListUpdated.observe(this, Observer {
            it ?: return@Observer

            rvItemOrderList.adapter?.notifyDataSetChanged()
        })
    }

    private fun initCategories() {
        val categories = preferenceManager.get("categories", emptyString()) as String
        val categoryResponse = Gson().fromJson(categories, CategoryResponse::class.java)

        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoryAdapter(
            context!!, categoryResponse.categories
        ) {
            displayProductsByCategory(it)
        }
    }

    private fun displayProductsByCategory(category: Category) {
        plantLog(Gson().toJson(category))
        viewModel.filterProductByCategory(category)
    }

    private fun initProducts() {
        val products = preferenceManager.get("products", emptyString()) as String
        val productResponse = Gson().fromJson(products, ProductResponse::class.java)

        viewModel.setProducts(productResponse.products)

        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = GridLayoutManager(activity, 4)
        rvProducts.adapter = ProductAdapter(
            context!!, viewModel.products
        ) {
            productSelected(it)
        }
    }

    private fun initItemOrderList() {
        rvItemOrderList.setHasFixedSize(true)
        rvItemOrderList.layoutManager = LinearLayoutManager(activity)
        rvItemOrderList.adapter = ItemOrderListAdapter(
            context!!, viewModel.orderList.orders
        ) {
            itemOrderSelected(it)
        }
    }

    private fun itemOrderSelected(itemOrder: ItemOrder) {

    }


    private fun productSelected(product: Product) {
        //
        // Process Add ons, Qty, Variants here
        //

        addOrder(ItemOrder(product, 1))
    }

    private fun addOrder(itemOrder: ItemOrder) {
        viewModel.addOrder(itemOrder)
    }

}