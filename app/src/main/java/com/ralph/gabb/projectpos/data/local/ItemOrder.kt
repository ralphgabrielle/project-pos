package com.ralph.gabb.projectpos.data.local

import com.ralph.gabb.projectpos.data.db.Product


/*
 * Created by Ralph Gabrielle Orden on 9/4/20.
 */

data class ItemOrder(

    var product: Product,

    var qty: Int

)