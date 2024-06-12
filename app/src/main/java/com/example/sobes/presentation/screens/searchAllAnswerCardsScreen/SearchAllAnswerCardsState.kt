package com.example.sobes.presentation.screens.searchAllAnswerCardsScreen

import com.example.sobes.domain.model.AnswerCard

data class SearchAllAnswerCardsState(
    val searchText: String = "",
    val listAnswerCards: List<AnswerCard> = emptyList()
)
