package com.ralph.gabb.projectpos.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */

abstract class BaseActivity : AppCompatActivity(), ConstructView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId?.let {
            setContentView(it)
            viewCreated()
        }
    }

}