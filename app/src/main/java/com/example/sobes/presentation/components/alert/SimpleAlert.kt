package com.example.sobes.presentation.components.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.sobes.presentation.color.SobesThemeObj

@Composable
fun SimpleAlert(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(shape = RoundedCornerShape(14.dp))
                .background(color = SobesThemeObj.colors.green),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        text = title,
                        textAlign = TextAlign.Center,
                        style = SobesThemeObj.typography.headLineSemiBold,
                        color = SobesThemeObj.colors.white,
                    )
                } else {
                    Spacer(modifier = Modifier.height(14.dp))
                }
                if (description != null) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 2.dp, start = 16.dp, end = 16.dp),
                        text = description,
                        color = SobesThemeObj.colors.white,
                        style = SobesThemeObj.typography.footNoteRegular,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                buttonText?.let {
                    TextForAcceptedBtnOnAlert(
                        text = buttonText,
                        onClick = { onButtonClick?.invoke() },
                    )
                }

                TextForCancelBtnOnAlert(
                    text = "Отмена",
                    onClick = onDismissRequest,
                )
            }
        }
    }
}

@Composable
fun TextForAcceptedBtnOnAlert(
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxSize(), color = SobesThemeObj.colors.gray2DarkAppearance
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp, top = 12.dp),
            text = text,
            color = SobesThemeObj.colors.backgroundDarkAppearance,
            textAlign = TextAlign.Center,
            style = SobesThemeObj.typography.headLineSemiBold,
        )
    }
}

@Composable
fun TextForCancelBtnOnAlert(
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxSize(), color = SobesThemeObj.colors.gray2DarkAppearance
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 12.dp, top = 12.dp),
            text = text,
            color = SobesThemeObj.colors.backgroundDarkAppearance,
            style = SobesThemeObj.typography.headline,
            textAlign = TextAlign.Center,
        )

    }
}

@Preview
@Composable
fun CustomAlertDialogPreview() {
    SimpleAlert(
        title = "Удаление секции",
        description = "Вы действительно хотите удалить этот раздел?",
        buttonText = "Да",
        onButtonClick = {},
        onDismissRequest = {}
    )
}

@Preview
@Composable
fun TextForCancelBtnOnAlertPreview() {
    TextForCancelBtnOnAlert(
        "Отмена", {}
    )
}
