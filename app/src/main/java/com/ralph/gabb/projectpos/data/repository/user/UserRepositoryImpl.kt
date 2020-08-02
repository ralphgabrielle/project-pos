package com.ralph.gabb.projectpos.data.repository.user

import androidx.lifecycle.LiveData
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.data.data_source.user.UserDataSource
import com.ralph.gabb.projectpos.http.Wrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/*
 * Created by Ralph Gabrielle Orden on 8/2/20.
 */

class UserRepositoryImpl(private val userDataSource: UserDataSource):
    UserRepository {

    override suspend fun loginAccount(loginBody: LoginBody): LiveData<out Wrapper<LoginResponse>> {
        return withContext(Dispatchers.IO) {
            return@withContext userDataSource.loginAccount(loginBody)
        }
    }

}