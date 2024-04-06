package com.ahuaman.integraterecaptchaandroidcompose

import android.app.Application
import android.util.Log
import com.google.android.recaptcha.Recaptcha
import com.google.android.recaptcha.RecaptchaAction
import com.google.android.recaptcha.RecaptchaClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object RecaptchaManager {

    private const val TAG = "RecaptchaManager"
    private lateinit var recaptchaClient: RecaptchaClient

    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    fun initializeRecaptcha(application: Application) = scope.launch {
        // Initialize Recaptcha
        Recaptcha.getClient(application,"6Ldd7rApAAAAAHggk4qKjXbf5r0ipDy7puKv1Al-")
            .onSuccess {
                Log.d(TAG, "Recaptcha initialized")
                recaptchaClient = it
            }.onFailure {
                // Handle error
                Log.e(TAG, "Recaptcha initialization failed", it)
            }

    }

    fun getRecaptchaClient(): RecaptchaClient {
        return recaptchaClient
    }
}