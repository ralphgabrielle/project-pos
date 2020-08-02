package com.ralph.gabb.projectpos.data.response

import com.ralph.gabb.projectpos.data.db.Branch
import com.ralph.gabb.projectpos.data.db.Store


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

data class LoginResponse (

    var token: String,

    var store: Store,

    var branch: Branch

)