package com.example.sobes.presentation.components.text_field

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.example.sobes.presentation.color.SobesThemeObj
import com.example.sobes.ui.theme.SobesTheme

@Composable
fun TextFieldTitle(modifier: Modifier = Modifier, title: AnnotatedString) {
    Text(
        modifier = modifier,
        text = title,
        style = SobesThemeObj.typography.callout,
        color = SobesThemeObj.colors.aquaBlue
    )
}

@Preview
@Composable
fun TextFieldTitlePreview() {
    SobesTheme {
        TextFieldTitle(title = buildAnnotatedString { append("Пример тайтла") })
    }
}