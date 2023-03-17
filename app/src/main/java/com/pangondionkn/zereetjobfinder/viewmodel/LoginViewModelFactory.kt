package com.pangondionkn.zereetjobfinder.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pangondionkn.zereetjobfinder.view.callback.LoginCallback
import java.lang.IllegalArgumentException

class LoginViewModelFactory(
    private val application: Application,
    private val callback: LoginCallback
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(application, callback) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}