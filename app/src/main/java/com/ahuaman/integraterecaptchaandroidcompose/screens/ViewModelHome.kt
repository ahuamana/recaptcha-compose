package com.ahuaman.integraterecaptchaandroidcompose.screens

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahuaman.integraterecaptchaandroidcompose.RecaptchaManager
import com.google.android.recaptcha.RecaptchaAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelHome : ViewModel() {

    private val _token = MutableStateFlow("")
    val token:StateFlow<String> = _token

    fun getToken() = viewModelScope.launch {
        RecaptchaManager.getRecaptchaClient()
            .execute(RecaptchaAction.custom("homepage"))
            .onSuccess {
                // Handle success
                Log.d("Recaptcha", "Success token: $it")
                _token.value = it
            }.onFailure {
                // Handle error
            }
    }
}