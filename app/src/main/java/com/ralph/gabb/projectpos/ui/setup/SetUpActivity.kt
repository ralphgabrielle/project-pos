package com.ralph.gabb.projectpos.ui.setup

import android.view.View
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import com.ralph.gabb.projectpos.data.body.TokenBody
import com.ralph.gabb.projectpos.extra.emptyString
import com.ralph.gabb.projectpos.ui.MainActivity
import com.ralph.gabb.projectpos.utils.PreferenceManager
import com.ralph.gabb.projectpos.utils.SuspendNetworkCall
import kotlinx.android.synthetic.main.activity_setup.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.ext.android.inject


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class SetUpActivity: BaseActivity() {

    private val viewModel : SetupViewModel by inject()
    private val preferenceManager : PreferenceManager by inject()

    override val layoutId: Int?
        get() = R.layout.activity_setup

    override fun viewCreated() {
        startSetup()
        listenToUserEvents()
    }

    private fun listenToUserEvents() {
        bGetStarted.setOnClickListener {
            getStarted()
        }
    }

    private fun getStarted() {
        startActivity(
            intentFor<MainActivity>()
                .clearTask()
                .newTask()
        )
    }

    private fun startSetup() {
        fetchCategories()
    }

    private fun fetchCategories() = CoroutineScope(Dispatchers.Main).launch {
        val token = preferenceManager.get("token", emptyString()) as String
        val fetchCategories = viewModel.fetchCategories(TokenBody(token))

        SuspendNetworkCall.makeCall(this@SetUpActivity, fetchCategories) {
            fetchProducts()
        }
    }

    private fun fetchProducts() = CoroutineScope(Dispatchers.Main).launch {
        val token = preferenceManager.get("token", emptyString()) as String
        val fetchProducts = viewModel.fetchProducts(TokenBody(token))

        SuspendNetworkCall.makeCall(this@SetUpActivity, fetchProducts) {

            setupComplete()
        }
    }

    private fun setupComplete() {
        bGetStarted.visibility = View.VISIBLE
        tvSetupHeader.visibility = View.INVISIBLE
    }
}