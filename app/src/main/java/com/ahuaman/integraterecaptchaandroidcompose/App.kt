package com.ahuaman.integraterecaptchaandroidcompose

import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        RecaptchaManager.initializeRecaptcha(this)
    }
}