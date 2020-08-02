package com.ralph.gabb.projectpos.extra

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import timber.log.Timber
import java.lang.Exception


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

fun plantLog(ex: Exception) {
    Timber.d(ex.message ?: "Null message logged")
}