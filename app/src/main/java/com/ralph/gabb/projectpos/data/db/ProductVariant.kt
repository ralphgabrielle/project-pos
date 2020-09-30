package com.ralph.gabb.projectpos.data.db

import com.google.gson.annotations.SerializedName


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

data class ProductVariant (

    var id: String,

    @SerializedName(value = "option_name")
    var name: String,

    var price: Double

)