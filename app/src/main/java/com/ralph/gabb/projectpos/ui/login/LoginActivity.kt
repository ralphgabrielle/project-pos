package com.ralph.gabb.projectpos.ui.login

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.local.QRCode
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.extra.readText
import com.ralph.gabb.projectpos.extra.simpleOK
import com.ralph.gabb.projectpos.extra.toast
import com.ralph.gabb.projectpos.ui.qr.QRCodeScannerActivity
import com.ralph.gabb.projectpos.ui.setup.SetUpActivity
import com.ralph.gabb.projectpos.utils.*
import com.ralph.gabb.projectpos.utils.Constant.REQUEST_QR_CODE
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.*
import org.koin.android.ext.android.inject

/*
 * Created by Ralph Gabrielle Orden on 2020-01-20.
 */

class LoginActivity: BaseActivity(), PermissionAccessManager.AccessPermission {

    private val viewModel : LoginViewModel by inject()
    private val preferenceManager: PreferenceManager by inject()

    private lateinit var permissionManageAccessManager: PermissionAccessManager

    private val cameraPermission = arrayOf(
        Manifest.permission.CAMERA)

    override val layoutId: Int?
        get() = R.layout.activity_login

    override fun viewCreated() {

        initPermissions()
        listenToEvents()
        observeEvents()

    }

    private fun initPermissions() {
        permissionManageAccessManager = PermissionAccessManager(
            context = this,
            requestCode = REQUEST_QR_CODE,
            permissions = cameraPermission,
            accessPermission = this
        )
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

        bQRCode.setOnClickListener {
            scanQRCode()
        }
    }

    private fun scanQRCode() {
        askDeviceCameraPermission()
    }

    private fun askDeviceCameraPermission() {

    }

    private fun login() {
        val username = etUsername.readText()
        val password = etPassword.readText()

        loginNow(username, password)
    }

    private fun loginNow(storeKey: String, branchKey: String) {
        viewModel.validateFields(storeKey, branchKey)
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

    private fun proceedToQRScan() {
        if (DeviceUtil.isMarshmallowOrAbove()) {
            askCameraPermission()
        } else {
            openQRSCan()
        }
    }

    private fun openQRSCan() {
        startActivityForResult<QRCodeScannerActivity>(Constant.REQUEST_QR_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.REQUEST_QR_CODE) {
                data?.let {
                    val stringExtra = it.getStringExtra("qr_code")
                    val qrCode = Gson().fromJson(stringExtra, QRCode::class.java)

                    loginNow(qrCode.storeKey, qrCode.branchKey)
                }
            }
        } else {
            simpleOK("Invalid QR Code", R.string.title_error)
        }
    }

    private fun askCameraPermission() {
        if (permissionManageAccessManager.isAllPermissionGranted()) {
            openQRSCan()
        } else {
            permissionManageAccessManager.checkPermission()
        }
    }

    override fun onAskPermission(permissions: Array<out String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    override fun onPermissionGranted(requestCode: Int) {
        when (requestCode) {
            REQUEST_QR_CODE -> proceedToQRScan()
        }
    }

    override fun onShowRequestRationale() {
        alert(R.string.message_permission_granted, R.string.title_error) {
            okButton {
                permissionManageAccessManager.openSettings()
            }
        }.show()
    }

    override fun onPermissionNotGrantedOnSome() {
        simpleOK(R.string.message_permission_granted, R.string.title_error)
    }
}