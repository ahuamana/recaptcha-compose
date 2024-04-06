package com.ahuaman.integraterecaptchaandroidcompose.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahuaman.integraterecaptchaandroidcompose.Greeting
import com.ahuaman.integraterecaptchaandroidcompose.RecaptchaManager
import com.google.android.recaptcha.RecaptchaAction

@Composable
fun HomeScreen(innerPadding: PaddingValues, onclick: () -> Unit){

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Greeting(
            name = "Welcome to Recaptcha Android Compose",
            modifier = Modifier.padding(innerPadding)
        )

        Button(onClick = { onclick() }) {
            Text(text = "Get Token from Recaptcha")
        }

        //Show toast with action

    }
}


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(innerPadding = PaddingValues(16.dp), onclick = {})
}