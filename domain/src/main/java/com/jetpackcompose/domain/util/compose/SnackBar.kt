package com.jetpackcompose.domain.util.compose

import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun getSnackBarHostState(
    channel: Channel<String>,
    result: (SnackbarResult) -> Unit
): SnackbarHostState {
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(channel) {
        channel.receiveAsFlow().collect { message ->
            result.invoke(
                snackBarHostState.showSnackbar(
                    message = message,
                    actionLabel = "close"
                )
            )
        }
    }
    return snackBarHostState
}

@Composable
fun getSnackBarCoroutineChannel() = remember { Channel<String>(Channel.CONFLATED) }