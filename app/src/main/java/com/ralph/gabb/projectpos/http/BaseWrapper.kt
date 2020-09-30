package com.ralph.gabb.projectpos.http

import com.ralph.gabb.projectpos.extra.emptyString

/*
 * Created by Ralph Gabrielle Orden on 3/18/2020.
 */

open class BaseWrapper<T> {

    var status: Int = 0

    var result: Int = 0

    var message: String = emptyString()

}