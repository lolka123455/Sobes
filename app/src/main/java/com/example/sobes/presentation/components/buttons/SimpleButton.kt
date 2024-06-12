package com.example.sobes.presentation.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj
import com.example.sobes.presentation.components.WhiteSmallLoader

@Composable
fun SimpleButton(
    buttonText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonColor: Color = if (isEnabled) SobesThemeObj.colors.green else SobesThemeObj.colors.green.copy(
        alpha = 0.4f
    ),
) {
    val buttonTextColor = if (isEnabled) Color.White else Color.White.copy(
        alpha = 0.4f
    )
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContainerColor = buttonColor
        ),
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = buttonText,
            style = SobesThemeObj.typography.headlineBold,
            overflow = TextOverflow.Ellipsis,
            color = buttonTextColor,
            maxLines = 1
        )
    }
}

@Composable
fun BigButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    buttonColor: Color = if (isEnabled) SobesThemeObj.colors.green else SobesThemeObj.colors.green.copy(
        alpha = 0.4f
    ),
) {
    SimpleButton(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 50.dp),
        buttonText = text,
        onClick = onClick,
        isEnabled = isEnabled,
        buttonColor = buttonColor
    )
}

@Preview
@Composable
private fun BigButtonEnabledPreview() {

    BigButton(
        text = "Войти",
        onClick = {}
    )
}

@Preview
@Composable
private fun BigButtonDisabledPreview() {
    BigButton(
        text = "Войти",
        onClick = {},
        isEnabled = false
    )
}

@Preview
@Composable
private fun SimpleButtonEnabledPreview() {

    SimpleButton(buttonText = "Войти", isEnabled = true, onClick = {})

}

@Preview
@Composable
private fun SimpleButtonDisabledPreview() {
    SimpleButton(buttonText = "Войти", isEnabled = false, onClick = {})

}