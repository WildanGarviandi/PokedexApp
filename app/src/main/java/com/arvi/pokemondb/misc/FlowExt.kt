package com.arvi.pokemondb.misc

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun <OneTimeEvent> Flow<OneTimeEvent?>.collectAsLifecycleAwareState(): OneTimeEvent? {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state = remember { mutableStateOf<OneTimeEvent?>(null) }

    DisposableEffect(lifecycleOwner) {
        val collector = this@collectAsLifecycleAwareState
        val job = lifecycleOwner.lifecycleScope.launch {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collector.collect {
                    state.value = it
                }
            }
        }
        onDispose {
            job.cancel()
        }
    }

    return state.value
}
