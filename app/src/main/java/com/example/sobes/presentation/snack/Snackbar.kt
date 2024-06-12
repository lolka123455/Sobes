package com.example.sobes.presentation.snack

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun Snackbar(
    data: SnackbarData,
) {
    Snackbar(
        modifier = Modifier
            .padding(horizontal = 8.dp),
        shape = MaterialTheme.shapes.large,
        containerColor = SobesThemeObj.colors.mainBlack,
        contentColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                style = SobesThemeObj.typography.body,
                text = data.message,
                color = SobesThemeObj.colors.white
            )
        }
    }
}