package com.ahuaman.integraterecaptchaandroidcompose

import android.content.ClipboardManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ahuaman.integraterecaptchaandroidcompose.screens.HomeScreen
import com.ahuaman.integraterecaptchaandroidcompose.screens.ViewModelHome
import com.ahuaman.integraterecaptchaandroidcompose.ui.theme.IntegrateRecaptchaAndroidComposeTheme
import com.google.android.recaptcha.RecaptchaAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val viewModel = viewModel<ViewModelHome>()
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { SnackbarHostState() }

            val tokenState by viewModel.token.collectAsState()

            LaunchedEffect(key1 = tokenState) {

                if(tokenState.isEmpty()){
                    return@LaunchedEffect
                }

                val result = snackbarHostState
                    .showSnackbar(
                        //only take the first 10 characters
                        message = "Token: ...${tokenState.takeLast(10)}",
                        actionLabel = "Copy",
                        duration = SnackbarDuration.Short
                    )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        //copy in clipboard token value

                        val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        clipboardManager.setPrimaryClip(
                            android.content.ClipData.newPlainText(
                                "Recaptcha Token",
                                viewModel.token.value
                            )
                        )


                    }
                    SnackbarResult.Dismissed -> {
                        /* Handle snackbar dismissed */
                    }
                }
            }

            IntegrateRecaptchaAndroidComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }) { innerPadding ->
                    HomeScreen(innerPadding = innerPadding, onclick = {
                        viewModel.getToken()
                    })
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntegrateRecaptchaAndroidComposeTheme {
        HomeScreen(innerPadding = PaddingValues(16.dp), onclick = {})
    }
}