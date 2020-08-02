package com.ralph.gabb.projectpos.ui.login

import androidx.lifecycle.Observer
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.extra.readText
import com.ralph.gabb.projectpos.extra.toast
import com.ralph.gabb.projectpos.ui.setup.SetUpActivity
import com.ralph.gabb.projectpos.utils.PreferenceManager
import com.ralph.gabb.projectpos.utils.SuspendNetworkCall
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.ext.android.inject

/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */

class LoginActivity: BaseActivity() {

    private val viewModel : LoginViewModel by inject()
    private val preferenceManager: PreferenceManager by inject()

    override val layoutId: Int?
        get() = R.layout.activity_login

    override fun viewCreated() {

        listenToEvents()
        observeEvents()

    }

    private fun observeEvents() {
        viewModel.validationErrorLiveData.observe(this, Observer {
            it ?: return@Observer
            toast(it)
        })

        viewModel.validationLiveData.observe(this, Observer {
            it ?: return@Observer

            loginAccount(it)
        })
    }

    private fun listenToEvents() {
        bLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val username = etUsername.readText()
        val password = etPassword.readText()

        viewModel.validateFields(username, password)
    }

    private fun loginAccount(loginBody: LoginBody) = CoroutineScope(Dispatchers.Main).launch {
        val loginAccount = viewModel.loginAccount(loginBody)

        SuspendNetworkCall.makeCall(this@LoginActivity, loginAccount)  {

            // will have a more simplified code later
            if (it.status == 200) {
                if (it.result == 1) {
                    loginSuccess(it.objectData)
                }
            }
        }
    }

    private fun loginSuccess(loginResponse: LoginResponse?) {
        loginResponse?.let {
            preferenceManager.set("token" to loginResponse.token)
            preferenceManager.set("has_user" to true)
            // save store and branch

            startDataSetup()
        }
    }

    private fun startDataSetup() {
        startActivity(
            intentFor<SetUpActivity>()
                .clearTask()
                .newTask()
        )
    }
}