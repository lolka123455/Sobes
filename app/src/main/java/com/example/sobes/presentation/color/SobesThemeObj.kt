package com.example.sobes.presentation.color

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object SobesThemeObj {

    val colors: CustomColors
        @Composable
        @ReadOnlyComposable
        get() = if (isSystemInDarkTheme()) {
            darkColors()
        } else {
//        lightColors()  //TODO убрать если хотим восстановить белую тему
            darkColors()
        }

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}