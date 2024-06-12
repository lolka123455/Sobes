package com.example.sobes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sobes.R

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    placeHolderText: String,
    searchText: String,
    onSearch: (String) -> Unit,
    onClear: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp),
            )
            .height(IntrinsicSize.Min)
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 8.dp, end = 6.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = Color.Unspecified,
        )
        Box {
            if (searchText.isEmpty()) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp, top = 8.dp, end = 16.dp),
                    text = placeHolderText,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, top = 8.dp, end = 12.dp)
                        .weight(1f),
                    value = searchText,
                    onValueChange = { onSearch(it) },
                    singleLine = true,
                    cursorBrush = SolidColor(Color.White)
                )

                if (searchText.isNotEmpty()) {
                    Icon(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(CircleShape)
                            .clickable(onClick = onClear),
                        painter = painterResource(id = R.drawable.ic_clear),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchViewPreview() {

    Column {
        SearchView(
            placeHolderText = "Поиск по названию и году выставки dfsdfdfsdfdsfsdfsdfsdfsdfsdf",
            searchText = "",
            onSearch = {},
            onClear = {},
        )

        SearchView(
            modifier = Modifier.padding(top = 12.dp),
            placeHolderText = "Поиск по названию и году выставки dfsdfdfsdfdsfsdfsdfsdfsdfsdf",
            searchText = "search text",
            onSearch = {},
            onClear = {},
        )
    }
}