package com.example.sobes.presentation.components.bottom_sheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.R
import com.example.sobes.presentation.color.SobesThemeObj

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleBottomSheet(
    title: String = "Создание карточки",
    alertContent: @Composable () -> Unit,
    sheetState: SheetState,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        scrimColor = Color(0xAA000000),
        dragHandle = { /* Optional drag handle */ }
    ) {
        Column(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 13.dp,
                        topEnd = 13.dp,
                    )
                )
                .background(SobesThemeObj.colors.green)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_indicator_bs),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 6.dp)
            )
            SimpleTitleForAlert(title)
            alertContent()
        }
    }
}

@Composable
fun SimpleTitleForAlert(
    text: String,
) {
    Column {
        Text(
            text = text,
            color = SobesThemeObj.colors.white,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 24.dp),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SimpleTextForBottomSheet(
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 24.dp),
            text = text,
            color = SobesThemeObj.colors.white,
            textAlign = TextAlign.Start,
            style = SobesThemeObj.typography.titleRegular,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SimpleBottomSheetPreview() {
    val bottomSheetState = rememberModalBottomSheetState()
    val showBottomSheet by remember { mutableStateOf(true) }

    if (showBottomSheet) {
        SimpleBottomSheet(
            alertContent = {
                Spacer(modifier = Modifier.height(28.dp))
                SimpleTextForBottomSheet("Редактировать", {})
                SimpleTextForBottomSheet("Удалить", {})
                Spacer(modifier = Modifier.height(28.dp))
            },
            sheetState = bottomSheetState,
            onDismissRequest = {}
        )
    }
}

@Preview
@Composable
fun SimpleTitleForAlertPreview() {
    SimpleTitleForAlert(
        "Сортировка"
    )
}