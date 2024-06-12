package com.example.sobes.presentation.snack

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter


sealed class CustomVisuals(
    override val message: String,
    override val actionLabel: String?,
    override val withDismissAction: Boolean,
    override val duration: SnackbarDuration,
    val alignment: Alignment.Vertical,
) : SnackbarVisuals

class SnackbarDataWithPainter(
    message: String,
    withDismissAction: Boolean,
    duration: SnackbarDuration,
    alignment: Alignment.Vertical = Alignment.Bottom,
    val painter: Painter,
) : CustomVisuals(
    message = message,
    actionLabel = null,
    withDismissAction = withDismissAction,
    duration = duration,
    alignment = alignment
)

class SnackbarData(
    message: String,
    withDismissAction: Boolean,
    duration: SnackbarDuration,
    alignment: Alignment.Vertical = Alignment.Bottom,
) : CustomVisuals(
    message = message,
    actionLabel = null,
    withDismissAction = withDismissAction,
    duration = duration,
    alignment = alignment
)