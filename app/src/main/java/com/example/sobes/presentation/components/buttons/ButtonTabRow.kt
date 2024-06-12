package com.example.sobes.presentation.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun ButtonTabRow(
    modifier: Modifier = Modifier,
    title: String,
    isEnabled: Boolean,
    onSelect: (String) -> Unit,
) {
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val textColor =
        if (!isSystemInDarkTheme && isEnabled) Color.White else SobesThemeObj.colors.white

    val colors = ButtonDefaults.buttonColors(
        containerColor = if (isEnabled) SobesThemeObj.colors.green
        else SobesThemeObj.colors.whiteTranslucent15
    )
    OutlinedButton(
        modifier = modifier.height(36.dp),
        colors = colors,
        enabled = true,
        border = BorderStroke(
            width = 1.dp,
            color = Color.Transparent
        ),
        shape = RoundedCornerShape(0),
        contentPadding = PaddingValues(horizontal = 12.dp),
        onClick = {
            onSelect(title)
        }
    ) {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor
        )
    }
}

@Preview
@Composable
fun ButtonTabRowPreview() {
    val onSelectDummy: (String) -> Unit = {}
    ButtonTabRow(
        title = "Tab Title",
        isEnabled = true,
        onSelect = onSelectDummy
    )
}