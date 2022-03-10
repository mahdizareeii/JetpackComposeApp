package com.jetpackcompose.app.presentation.ui.speech

import android.content.Context
import android.speech.RecognitionListener

abstract class SpeechHelper {
    abstract fun checkSpeechAvailability(context: Context): Boolean
    abstract fun createSpeechRecognizer(context: Context, listener: RecognitionListener)
    abstract fun startListening(language:String)

    companion object {
        private var instance: SpeechHelperImpl? = null
        fun create() = if (instance == null) {
            instance = SpeechHelperImpl()
            instance
        } else instance
    }
}