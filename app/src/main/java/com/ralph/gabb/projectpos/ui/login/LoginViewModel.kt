package com.ralph.gabb.projectpos.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ralph.gabb.projectpos.data.body.LoginBody
import com.ralph.gabb.projectpos.data.response.LoginResponse
import com.ralph.gabb.projectpos.data.repository.user.UserRepository
import com.ralph.gabb.projectpos.http.Wrapper

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {

    val validationErrorLiveData = MutableLiveData<String>()
    val validationLiveData = MutableLiveData<LoginBody>()

    fun validateFields(username: String, password: String) {
        if (username.isEmpty()) {
            validationErrorLiveData.postValue("Enter your username.")
            return
        }

        if (password.isEmpty()) {
            validationErrorLiveData.postValue("Enter your username.")
            return
        }

        validationLiveData.postValue(
            LoginBody(
                username,
                password
            )
        )
    }

    suspend fun loginAccount(loginBody: LoginBody) : LiveData<out Wrapper<LoginResponse>> {
        return userRepository.loginAccount(loginBody)
    }
}