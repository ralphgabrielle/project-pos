package com.ralph.gabb.projectpos.data.response

import com.ralph.gabb.projectpos.data.db.Product


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class ProductResponse (

    var products: List<Product> = listOf()

)