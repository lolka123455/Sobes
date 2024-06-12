package com.example.sobes.presentation.components.text_field

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.presentation.color.SobesThemeObj
import com.example.sobes.ui.theme.SobesTheme

@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    keyboard: KeyboardOptions = KeyboardOptions(),
    value: String,
    maxLines: Int = 1,
    onTextChange: (String) -> Unit,
    isErrorVisible: Boolean = false,
    errorMessage: String = "",
    isReadOnly: Boolean = false,
    onClick: (() -> Unit)? = null,
    textColor: Color = SobesThemeObj.colors.white
) {
    val combinedModifier = if (isReadOnly) {
        modifier.clickable { onClick?.invoke() }
    } else {
        modifier
    }

    Column(modifier = combinedModifier.fillMaxWidth()) {
        BasicTextField(
            modifier = Modifier
                .padding(bottom = 10.dp, top = 8.dp)
                .fillMaxWidth(),
            value = value,
            maxLines = maxLines,
            textStyle = SobesThemeObj.typography.callout.copy(color = textColor),
            onValueChange = { if (!isReadOnly) onTextChange(it) },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.error),
            keyboardOptions = keyboard.copy(imeAction = if (isReadOnly) ImeAction.None else keyboard.imeAction),
            readOnly = isReadOnly
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp), color = SobesThemeObj.colors.blueGrey
        )
        AnimatedVisibility(visible = isErrorVisible) {
            Text(
                text = errorMessage,
                style = SobesThemeObj.typography.caption0,
                color = SobesThemeObj.colors.red
            )
        }
    }
}

@Composable
fun SimpleTextFieldWithTitle(
    modifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions(),
    keyboard: KeyboardOptions = KeyboardOptions(),
    value: String,
    textFieldTitle: String,
    maxLines: Int = 1,
    isObligatory: Boolean = false,
    onTextChange: (String) -> Unit,
    isErrorVisible: Boolean = false,
    errorMessage: String = "",
    showVisibilityIcon: ImageVector? = null,
    hideVisibilityIcon: ImageVector? = null,
    isPassword: Boolean = false,
    isBigHeightField: Boolean = false,
    heightValueField: Int = 0,
    isReadOnly: Boolean = false,
    onClick: (() -> Unit)? = null,
    isNoTrim: Boolean = false,
    isNoTrimWithStart: Boolean = false,
    textColor: Color = SobesThemeObj.colors.white
) {
    val combinedModifier = if (isReadOnly) {
        modifier.clickable { onClick?.invoke() }
    } else {
        modifier
    }
    Column(modifier = combinedModifier.fillMaxWidth()) {
        val titleString =
            if (isObligatory) {
                annotatedStringWithStar(textFieldTitle)
            } else {
                buildAnnotatedString {
                    append(textFieldTitle)
                }
            }
        val dividerColor =
            if (isErrorVisible) SobesThemeObj.colors.red else SobesThemeObj.colors.blueGrey

        val (passwordVisible, setPasswordVisible) = remember { mutableStateOf(false) }

        TextFieldTitle(title = titleString)
        if (isBigHeightField) Spacer(modifier = Modifier.height(heightValueField.dp))
        Column {
            Box {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 8.dp),
                    value = value,
                    maxLines = maxLines,
                    singleLine = maxLines == 1,
                    textStyle = SobesThemeObj.typography.callout.copy(color = textColor),
                    onValueChange = {
                        if (!isReadOnly) {
                            val newValue = when {
                                isNoTrim -> it.replace(" ", "")
                                isNoTrimWithStart && it.startsWith(" ") -> value
                                else -> it
                            }
                            onTextChange(newValue)
                        }
                    },
                    cursorBrush = if (isErrorVisible) SolidColor(SobesThemeObj.colors.red) else SolidColor(
                        SobesThemeObj.colors.brightBlue
                    ),
                    keyboardOptions = keyboard.copy(imeAction = if (isReadOnly) ImeAction.None else keyboard.imeAction),
                    keyboardActions = if (isReadOnly) KeyboardActions() else keyboardActions,
                    visualTransformation = VisualTransformation.None,
                    readOnly = isReadOnly
                )
                if (isPassword) {
                    val icon = if (passwordVisible) hideVisibilityIcon else showVisibilityIcon
                    icon?.let {
                        IconButton(
                            onClick = { setPasswordVisible(!passwordVisible) },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(24.dp)
                                .padding(end = 8.dp)
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = "Переключить видимость пароля",
                                tint = SobesThemeObj.colors.aquaBlue
                            )
                        }
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
                    .height(1.dp), color = dividerColor
            )
        }

        AnimatedVisibility(visible = isErrorVisible) {
            Text(
                text = errorMessage,
                style = SobesThemeObj.typography.caption0,
                color = SobesThemeObj.colors.red
            )
        }
    }
}

@Composable
private fun annotatedStringWithStar(title: String) = buildAnnotatedString {
    append(title)
    withStyle(style = SpanStyle(SobesThemeObj.colors.aquaBlue)) {
        append(" *")
    }
}

@Preview
@Composable
fun TextFieldPreview() {
    SobesTheme() {
        SimpleTextField(modifier = Modifier, value = "", onTextChange = {})
    }
}

@Preview
@Composable
fun TextFieldWithTitlePreview() {
    SobesTheme() {
        SimpleTextFieldWithTitle(
            modifier = Modifier,
            value = "Всем привет",
            textFieldTitle = "Test",
            isObligatory = false,
            onTextChange = {},
            hideVisibilityIcon = null
        )
    }
}

@Preview
@Composable
fun TextFieldWithErrorPreview() {
    SobesTheme() {
        SimpleTextFieldWithTitle(
            modifier = Modifier,
            value = "",
            textFieldTitle = "Test",
            isObligatory = false,
            onTextChange = {},
            errorMessage = "Ошибка валидации",
            isErrorVisible = true
        )
    }
}

@Preview
@Composable
fun TextFieldWithTitleObligatoryPreview() {
    SobesTheme() {
        SimpleTextFieldWithTitle(
            modifier = Modifier,
            value = "",
            textFieldTitle = "Test",
            isObligatory = true,
            onTextChange = {})
    }
}