package com.ralph.gabb.projectpos.ui.main

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseFragment
import com.ralph.gabb.projectpos.data.db.Product
import com.ralph.gabb.projectpos.data.response.CategoryResponse
import com.ralph.gabb.projectpos.data.response.ProductResponse
import com.ralph.gabb.projectpos.extra.emptyString
import com.ralph.gabb.projectpos.utils.PreferenceManager
import kotlinx.android.synthetic.main.fragment_shop.*
import org.koin.android.ext.android.inject


/*
 * Created by Ralph Gabrielle Orden on 8/3/20.
 */

class ShopFragment: BaseFragment() {

    private val preferenceManager: PreferenceManager by inject()

    override val layoutId: Int?
        get() = R.layout.fragment_shop

    override fun viewCreated() {
        initCategories()
        initProducts()
    }

    private fun initCategories() {
        val categories = preferenceManager.get("categories", emptyString()) as String
        val categoryResponse = Gson().fromJson(categories, CategoryResponse::class.java)

        rvCategories.setHasFixedSize(true)
        rvCategories.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = CategoryAdapter(context!!, categoryResponse.categories)
    }

    private fun initProducts() {
        val products = preferenceManager.get("products", emptyString()) as String
        val productResponse = Gson().fromJson(products, ProductResponse::class.java)

        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = GridLayoutManager(activity, 4)
        rvProducts.adapter = ProductAdapter(context!!, productResponse.products) {
            productSelected(it)
        }
    }

    private fun productSelected(product: Product) {
        
    }

}