package com.ralph.gabb.projectpos

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int?

    abstract fun viewCreated()

}