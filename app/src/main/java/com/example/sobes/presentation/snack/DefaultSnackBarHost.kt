package com.example.sobes.presentation.snack

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DefaultSnackBarHost(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
) = SnackbarHost(
    modifier = modifier,
    hostState = snackBarHostState,
) {
    when (it.visuals) {
        is SnackbarDataWithPainter -> {
            SnackbarWithPainter(it.visuals as SnackbarDataWithPainter)
        }
        is SnackbarData -> {
            Snackbar(it.visuals as SnackbarData)
        }
    }
}