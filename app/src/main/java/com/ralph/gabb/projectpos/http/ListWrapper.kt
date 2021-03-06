package com.ralph.gabb.projectpos.http

import com.google.gson.annotations.SerializedName

/*
 * Created by Ralph Gabrielle Orden on 3/18/2020.
 */

class ListWrapper<T> : BaseWrapper<T>() {

    @SerializedName("data")
    var dataList = listOf<T>()

}