package com.ahuaman.integraterecaptchaandroidcompose.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.integraterecaptchaandroidcompose.RecaptchaManager
import com.google.android.recaptcha.RecaptchaAction
import kotlinx.coroutines.launch

class ViewModelHome : ViewModel() {


    fun getToken() = viewModelScope.launch {
        RecaptchaManager.getRecaptchaClient()
            .execute(RecaptchaAction.custom("homepage"))
            .onSuccess {
                // Handle success
                Log.d("Recaptcha", "Success token: $it")
            }.onFailure {
                // Handle error
            }
    }
}