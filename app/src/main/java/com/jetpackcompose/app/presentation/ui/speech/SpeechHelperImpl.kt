package com.jetpackcompose.app.presentation.ui.speech

import android.content.Context
import android.content.Intent
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer

class SpeechHelperImpl : SpeechHelper() {
    private lateinit var speech: SpeechRecognizer
    override fun checkSpeechAvailability(context: Context): Boolean {
        return SpeechRecognizer.isRecognitionAvailable(context)
    }

    override fun createSpeechRecognizer(context: Context, listener: RecognitionListener) {
        speech = SpeechRecognizer.createSpeechRecognizer(context)
        speech.setRecognitionListener(listener)
    }

    override fun startListening(language: String) {
        speech.startListening(getRecognizerIntent(language))
    }

    private fun getRecognizerIntent(language: String) =
        Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, language)
        }
}