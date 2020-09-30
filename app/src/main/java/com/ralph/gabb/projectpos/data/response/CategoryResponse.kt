package com.ralph.gabb.projectpos.data.response

import com.ralph.gabb.projectpos.data.db.Category


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class CategoryResponse (

    var categories: List<Category> = listOf()

)