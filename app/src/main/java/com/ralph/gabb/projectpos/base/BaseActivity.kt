package com.ralph.gabb.projectpos.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */
abstract class BaseActivity : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int?

    abstract fun viewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.let {
            setContentView(it)
            viewCreated()
        }
    }

}