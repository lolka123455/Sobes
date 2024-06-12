package com.example.sobes.presentation.components.toolbars

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.R
import com.example.sobes.presentation.color.SobesThemeObj


@Composable
fun MultiActionToolbar(
    @StringRes titleId: Int? = null,
    onLeftBtnClick: (() -> Unit)? = null,
    onRightBtnClick: (() -> Unit)? = null,
    onMiddleBtnClick: (() -> Unit)? = null,
    leftIconDrawable: Int = R.drawable.ic_chevron_left,
    rightIconDrawable: Int = R.drawable.ic_plus,
    middleIconDrawable: Int = R.drawable.ic_trash,
    isTopBarVisible: Boolean = true,
    showRightIcon: Boolean = true,
    showLeftIcon: Boolean = true,
    showMiddleIcon: Boolean = true,
) {
    if (isTopBarVisible)
        MultiActionToolbar(
            title = titleId?.let { stringResource(id = it) },
            leftIconDrawable = leftIconDrawable,
            rightIconDrawable = rightIconDrawable,
            middleIconDrawable = middleIconDrawable,
            onBackClick = onLeftBtnClick,
            onRightBtnClick = onRightBtnClick,
            onMiddleBtnClick = onMiddleBtnClick,
            showLeftIcon = showLeftIcon,
            showRightIcon = showRightIcon,
            showDeleteIcon = showMiddleIcon,
        )
}

@Composable
fun MultiActionToolbar(
    title: String? = null,
    onBackClick: (() -> Unit)? = null,
    onRightBtnClick: (() -> Unit)? = null,
    onMiddleBtnClick: (() -> Unit)? = null,
    leftIconDrawable: Int = R.drawable.ic_chevron_left,
    rightIconDrawable: Int = R.drawable.ic_plus,
    middleIconDrawable: Int = R.drawable.ic_trash,
    showLeftIcon: Boolean = true,
    showRightIcon: Boolean = true,
    showDeleteIcon: Boolean = true,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.statusBars)
            .heightIn(min = 56.dp),
        contentAlignment = Alignment.Center
    ) {
        if (showLeftIcon) {
            onBackClick?.let {
                Icon(
                    painter = painterResource(leftIconDrawable),
                    contentDescription = "back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .clickable(onClick = it)
                        .padding(start = 16.dp, top = 9.dp, bottom = 9.dp)
                        .align(Alignment.CenterStart)
                )
            }
        }

        title?.let {
            Text(
                text = it,
                style = SobesThemeObj.typography.title3.copy(fontWeight = FontWeight.SemiBold),
                color = SobesThemeObj.colors.white,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if (showDeleteIcon) {
            onMiddleBtnClick?.let {
                Icon(
                    painter = painterResource(middleIconDrawable),
                    contentDescription = "trash",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(end = 65.dp, top = 9.dp, bottom = 9.dp)
                        .clickable(onClick = it)
                        .align(Alignment.CenterEnd)
                )
            }
        }

        if (showRightIcon) {
            onRightBtnClick?.let {
                Icon(
                    painter = painterResource(rightIconDrawable),
                    contentDescription = "edit",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(end = 16.dp, top = 9.dp, bottom = 9.dp)
                        .clickable(onClick = it)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MainToolbarPreview() {

    MultiActionToolbar(
        titleId = R.string.app_name,
        onLeftBtnClick = {},
        onRightBtnClick = {},
        onMiddleBtnClick = {})

}