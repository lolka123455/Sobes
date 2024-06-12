package com.example.sobes.presentation.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Card(
    modifier: Modifier = Modifier,
    name: String,
    countCard: String,
    onCardClick: () -> Unit,
    onMoreOptionsClick: () -> Unit
) {
    Column(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxSize()
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onCardClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .weight(1f),
                text = name,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = Color.White,
                modifier = Modifier
                    .clickable { onMoreOptionsClick() }
            )
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Row {
            Text(
                modifier = Modifier.padding(bottom = 4.dp, end = 5.dp),
                text = "Количество карточек",
                color = Color.White
            )
            Text(
                text = countCard,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    Card(
        name = "Пример карточки",
        countCard = "10",
        onCardClick = {},
        onMoreOptionsClick = {}
    )
}
