package com.ralph.gabb.projectpos.data.data_source.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.data_source.user.UserDataSource
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.http.AppNetworkService
import com.ralph.gabb.projectpos.http.Wrapper


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class UserDataSourceImpl(private val networkService: AppNetworkService) :
    UserDataSource {

    override suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>> {
        val value = MutableLiveData<Wrapper<LoginResponse>>()
        val suspendCall = networkService.loginAccount(loginBody)

        value.postValue(suspendCall)
        return value
    }

}