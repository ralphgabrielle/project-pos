package com.ralph.gabb.projectpos.ui

import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */
class LoginActivity: BaseActivity() {

    override val layoutId: Int?
        get() = R.layout.activity_login

    override fun viewCreated() {

        listenToEvents()

    }

    private fun listenToEvents() {
        bLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {

    }

}