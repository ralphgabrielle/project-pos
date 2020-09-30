package com.ralph.gabb.projectpos.extra

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import timber.log.Timber
import java.lang.Exception
import java.text.DecimalFormat


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */
 
fun EditText.readText(): String {
    return this.text.toString()
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun emptyString() = ""

fun Double.formatCurrency() : String {
    val decimalFormat = DecimalFormat("Php ###,##0.00")
    return decimalFormat.format(this)
}

fun plantLog(ex: Exception) {
    Timber.d(ex.message ?: "Null message logged")
}

fun plantLog(str: String) {
    Timber.d(str ?: "Null message logged")
}

fun Context.simpleOK(messageResource: String, titleResource: String) {
    alert(messageResource, titleResource) {
        okButton {}
    }.show()
}

fun Context.simpleOK(messageResource: Int, titleResource: Int) {
    alert(messageResource, titleResource) {
        okButton {}
    }.show()
}

fun Context.simpleOK(message: String, titleResource: Int) {
    alert(message, getString(titleResource)) {
        okButton {}
    }.show()
}
