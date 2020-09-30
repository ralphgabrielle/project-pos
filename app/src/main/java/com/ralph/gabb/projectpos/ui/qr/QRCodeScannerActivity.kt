package com.ralph.gabb.projectpos.ui.qr

import android.app.Activity
import android.content.Intent
import android.graphics.PointF
import com.dlazaro66.qrcodereaderview.QRCodeReaderView
import com.ralph.gabb.projectpos.R
import com.ralph.gabb.projectpos.base.BaseActivity
import kotlinx.android.synthetic.main.activity_qr_code.*


/*
 * Created by Ralph Gabrielle Orden on 9/8/20.
 */

class QRCodeScannerActivity: BaseActivity(), QRCodeReaderView.OnQRCodeReadListener {

    private var hasScanned = false

    override val layoutId: Int?
        get() = R.layout.activity_qr_code

    override fun viewCreated() {
        qrCode.setAutofocusInterval(2000L)
        qrCode.setOnQRCodeReadListener(this)
    }

    override fun onResume() {
        super.onResume()
        hasScanned = false
        qrCode.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qrCode.stopCamera()
    }

    override fun onQRCodeRead(text: String?, points: Array<out PointF>?) {
        qrCode.stopCamera()

        if (hasScanned) return
        hasScanned = true

        returnResult(text)
    }

    private fun returnResult(qrCode: String?) {
        qrCode?.let {
            Intent().let {
                it.putExtra("qr_cod", qrCode)
                setResult(Activity.RESULT_OK)

                finish()
            }
        }
    }
}