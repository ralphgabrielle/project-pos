package com.ralph.gabb.projectpos.ui.start

import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import com.ralph.gabb.projectpos.ui.MainActivity
import com.ralph.gabb.projectpos.ui.setup.SetUpActivity
import com.ralph.gabb.projectpos.utils.PreferenceManager
import kotlinx.coroutines.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.koin.android.ext.android.inject


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class StartActivity: BaseActivity() {

    private val preferenceManager: PreferenceManager by inject()

    override val layoutId: Int?
        get() = R.layout.activity_start

    override fun viewCreated() {
        CoroutineScope(Dispatchers.IO).launch {

            // delay 3 seconds
            delay(3000)

            withContext(Dispatchers.Main) {
                val hasAccount = preferenceManager.get("has_account", false) as Boolean

                if (hasAccount) {
                    displayMain()
                } else {
                    displayLogin()
                }
            }
        }
    }

    private fun displayLogin() {
        startActivity(
            intentFor<SetUpActivity>()
                .clearTask()
                .newTask()
        )
    }

    private fun displayMain() {
        startActivity(
            intentFor<MainActivity>()
                .clearTask()
                .newTask()
        )
    }
}