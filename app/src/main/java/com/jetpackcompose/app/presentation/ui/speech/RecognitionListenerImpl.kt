package com.jetpackcompose.app.presentation.ui.speech

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.util.Log


class RecognitionListenerImpl(private val result: (String) -> Unit) : RecognitionListener {
    private val tag = "SPEECH_LISTENER"
    override fun onReadyForSpeech(params: Bundle?) {
        Log.i(tag, "onReadyForSpeech: ${params.toString()}")
    }

    override fun onBeginningOfSpeech() {
        Log.i(tag, "onBeginningOfSpeech")
    }

    override fun onRmsChanged(rmsdB: Float) {
        Log.i(tag, "onRmsChanged: $rmsdB")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        Log.i(tag, "onBufferReceived: $buffer")
    }

    override fun onEndOfSpeech() {
        Log.i(tag, "onEndOfSpeech")
    }

    override fun onError(error: Int) {
        val message = when (error) {
            SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
            SpeechRecognizer.ERROR_CLIENT -> "Client side error"
            SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
            SpeechRecognizer.ERROR_NETWORK -> "Network error"
            SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
            SpeechRecognizer.ERROR_NO_MATCH -> "No match"
            SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
            SpeechRecognizer.ERROR_SERVER -> "error from server"
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
            else -> "Didn't understand, please try again."
        }
        Log.i(tag, "onError: $message")
        result.invoke(message)
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        var text = ""
        for (result in matches!!) text += "$result \n"
        Log.i(tag, "onResults: $text")
        result.invoke(text)
    }

    override fun onPartialResults(partialResults: Bundle?) {
        Log.i(tag, "onPartialResults: ${partialResults.toString()}")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        Log.i(tag, "onEvent: eventType = $eventType AND params = ${params.toString()}")
    }
}