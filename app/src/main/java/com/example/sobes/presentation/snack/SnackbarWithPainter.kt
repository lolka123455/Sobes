package com.example.sobes.presentation.snack

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun SnackbarWithPainter(
    data: SnackbarDataWithPainter,
) {

    Snackbar(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .requiredHeight(64.dp),
        shape = MaterialTheme.shapes.large,
        containerColor = SobesThemeObj.colors.mainBlack,
        contentColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = data.painter,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                modifier = Modifier.weight(1f),
                style = SobesThemeObj.typography.body,
                text = data.message,
                color = SobesThemeObj.colors.white
            )


        }

        Spacer(modifier = Modifier.width(4.dp))
    }
}