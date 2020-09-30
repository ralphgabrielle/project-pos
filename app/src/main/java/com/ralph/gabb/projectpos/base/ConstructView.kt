package com.ralph.gabb.projectpos.base

import androidx.annotation.LayoutRes


/*
 * Created by Ralph Gabrielle Orden on 9/5/20.
 */

interface ConstructView {

    @get:LayoutRes
    val layoutId: Int?

    fun viewCreated()

}