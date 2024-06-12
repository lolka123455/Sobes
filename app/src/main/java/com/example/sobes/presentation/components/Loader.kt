package com.example.sobes.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = SobesThemeObj.colors.blueDarkAppearance
        )
    }
}

@Composable
fun SmallLoader(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(32.dp),
        color = SobesThemeObj.colors.blueDarkAppearance
    )
}

@Composable
fun WhiteSmallLoader(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.size(32.dp),
        color = SobesThemeObj.colors.white
    )
}