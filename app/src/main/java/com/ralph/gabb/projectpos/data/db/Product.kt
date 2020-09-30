package com.ralph.gabb.projectpos.data.db

import com.google.gson.annotations.SerializedName


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class Product (

    var id: String,

    @SerializedName(value = "product_name")
    var productName: String,

    @SerializedName(value = "store_id")
    var storeId: String,

    @SerializedName(value = "branch_id")
    var branchId: String,

    @SerializedName(value = "price")
    var productPrice: String?,

    @SerializedName(value = "image")
    var imageUrl: String?,

    @SerializedName(value = "category_id")
    var categoryId: Int,

    var variants : ArrayList<ProductVariant> = ArrayList()

)