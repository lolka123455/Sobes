package com.example.sobes.presentation.components.text_field

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.R
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    title: Int = R.string.app_name,
    description: String,
    onTextChanged: (String) -> Unit,
    isReadOnly: Boolean = false
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = description)) }
    val textFieldValue = textFieldValueState.copy(text = description)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }

    var lastTextValue by remember(description) { mutableStateOf(description) }

    DescriptionField(
        modifier = modifier,
        title = title,
        comment = textFieldValue,
        onTextChanged = { newTextFieldValueState ->
            if (!isReadOnly) {
                textFieldValueState = newTextFieldValueState

                val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
                lastTextValue = newTextFieldValueState.text

                if (stringChangedSinceLastInvocation) {
                    onTextChanged(newTextFieldValueState.text)
                }
            }
        },
        isReadOnly = isReadOnly
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    title: Int = R.string.app_name,
    comment: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    isReadOnly: Boolean = false
) {
    Column(modifier = modifier) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Text(
                text = stringResource(title),
                color = SobesThemeObj.colors.blueFootnotes,
                style = SobesThemeObj.typography.footNoteRegular,
            )
            Row {
                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .background(color = Color.Transparent),
                    value = comment,
                    onValueChange = { if (!isReadOnly) onTextChanged(it) },
                    cursorBrush = SolidColor(SobesThemeObj.colors.white),
                    textStyle = SobesThemeObj.typography.bodyRegular.copy(color = SobesThemeObj.colors.white),
                    readOnly = isReadOnly
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = SobesThemeObj.colors.blueFootnotes
            )
        }
    }
}

@Preview
@Composable
fun CommentFieldPreview() {
    DescriptionField(
        title = R.string.app_name,
        description = "Всем привет",
        onTextChanged = {},
        isReadOnly = true
    )
}
