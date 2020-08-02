package com.ralph.gabb.projectpos.data.data_source.user

import androidx.lifecycle.LiveData
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.http.Wrapper


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

interface UserDataSource {

    suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>>

}