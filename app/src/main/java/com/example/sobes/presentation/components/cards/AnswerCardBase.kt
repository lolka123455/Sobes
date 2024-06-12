package com.example.sobes.presentation.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnswerCardBase(
    modifier: Modifier = Modifier,
    name: String,
    isShowSettingsIcon: Boolean = true,
    descriptionCard: String,
    onCardClick: () -> Unit,
    onMoreOptionsClick: () -> Unit
) {
    Column(
        modifier = modifier
            .height(IntrinsicSize.Min)
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
            )
            if (isShowSettingsIcon) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = Color.White,
                    modifier = Modifier
                        .clickable { onMoreOptionsClick() }
                )
            }
        }
        Spacer(modifier = Modifier.padding(top = 10.dp))
        Text(
            text = if (descriptionCard.length > 150) descriptionCard.take(150) + "..." else descriptionCard,
            color = Color.White,
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}

@Preview
@Composable
fun AnswerCardSmallPreview() {
    AnswerCardBase(
        name = "Пример карточки",
        descriptionCard = "Какой то дескрипшен не особо длинный",
        onCardClick = {},
        onMoreOptionsClick = {}
    )
}