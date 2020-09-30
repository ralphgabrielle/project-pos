package com.ralph.gabb.projectpos.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.ralph.gabb.projectpos.extra.plantLog
import com.ralph.gabb.projectpos.http.BaseWrapper
import java.lang.Exception


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

object SuspendNetworkCall{

    fun <T> makeCall (context: Context, liveDataCall: LiveData<out T>, onResponse: (T) -> Unit) {
        try {
            liveDataCall.observe(context as LifecycleOwner, Observer {
                it?: return@Observer
                onResponse(it)
                liveDataCall.removeObservers(context as LifecycleOwner)
            })
        } catch (ex: Exception) {
            plantLog(ex)
        }
    }

}