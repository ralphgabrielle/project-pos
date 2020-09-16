package com.ralph.gabb.projectpos.utils

import android.os.Build


/*
 * Created by Ralph Gabrielle Orden on 9/8/20.
 */

object DeviceUtil {

    fun isMarshmallowOrAbove() : Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

}