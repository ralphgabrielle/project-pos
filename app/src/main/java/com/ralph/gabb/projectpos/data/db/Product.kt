package com.ralph.gabb.projectpos.data.db

import com.google.gson.annotations.SerializedName


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class Product (

    var id: String,

    @SerializedName(value = "product_name")
    var productName: String,

    var image: String?,

    @SerializedName(value = "store_id")
    var storeId: String,

    @SerializedName(value = "branch_id")
    var branchId: String

)