package com.ralph.gabb.projectpos.data.db

import com.google.gson.annotations.SerializedName


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class Store (

    var id: String,

    var name: String,

    @SerializedName(value = "user_id")
    var userId: String,

    @SerializedName(value = "created_at")
    var createdAt: String,

    @SerializedName(value = "updated_at")
    var updatedAt: String,

    @SerializedName(value = "multi_branch")
    var multiBranch: Int,

    @SerializedName(value = "subscription_id")
    var subscriptionId: Int,

    @SerializedName(value = "unique_key")
    var uniqueKey: String


)