package com.jetpackcompose.app.presentation.ui

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.jetpackcompose.app.presentation.ui.speech.RecognitionListenerImpl
import com.jetpackcompose.app.presentation.ui.speech.SpeechHelper
import com.jetpackcompose.resources.theme.AppTheme
import com.jetpackcompose.resources.theme.textColor
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var speechHelper: SpeechHelper? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            speechHelper = SpeechHelper.create()
        } else {
            Toast.makeText(this, "PERMISSION_NOT_GRANTED", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        setContent {
            AppTheme {
                var text by remember {
                    mutableStateOf("")
                }
                Column(modifier = Modifier.fillMaxSize()) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            startListening {
                                text = it
                            }
                        }
                    ) {
                        Text(
                            text = "start",
                            color = MaterialTheme.colors.textColor
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = text,
                        color = Color.Red
                    )
                }
            }
        }
    }

    private fun startListening(text: (String) -> Unit) {
        if (speechHelper?.checkSpeechAvailability(this) == true) {
            speechHelper?.createSpeechRecognizer(this, RecognitionListenerImpl(text))
            speechHelper?.startListening("en-US")
        } else {
            Toast.makeText(this, "UnAvailable speech", Toast.LENGTH_SHORT).show()
        }
    }
}