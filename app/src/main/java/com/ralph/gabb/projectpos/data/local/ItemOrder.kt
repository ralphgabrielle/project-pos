package com.ralph.gabb.projectpos.data.local

import com.ralph.gabb.projectpos.data.db.Product
import com.ralph.gabb.projectpos.data.db.ProductVariant


/*
 * Created by Ralph Gabrielle Orden on 9/4/20.
 */

data class ItemOrder(

    var product: Product,

    var qty: Int,

    var total: Double = 0.0,

    var variant: ProductVariant

)