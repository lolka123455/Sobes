package com.example.sobes.presentation.screens.editAnswerCard

data class EditAnswerCardState (
    val title: String? = "",
    val description: String? = "",
    val isDeleteAlertOpen: Boolean = false,
    val isNormalMode: Boolean = false
)