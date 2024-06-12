package com.example.sobes.presentation.screens.answerCardsForSection

import androidx.compose.runtime.Stable
import com.example.sobes.domain.model.AnswerCard

@Stable
data class AnswerCardsForSectionState (
    val searchText : String = "",
    val newAnswerCardName : String = "",
    val newAnswerCardDescription : String = "",
    val sectionId : String = "",
    val selectedAnswerCardUUID: String = "",
    val isAddedNewAnswerCardBottomSheetOpen: Boolean = false,
    val isSettingsAlertOpen: Boolean = false,
    val listAnswerCards : List<AnswerCard> = emptyList()
)