package com.example.sobes.presentation.screens.home

import androidx.compose.runtime.Stable
import com.example.sobes.domain.model.CardInfo

@Stable
data class HomeScreenState(
    val searchText : String = "",
    val listSections : List<CardInfo> = emptyList(),
    val newSectionName : String = "",
    val isAddedNewSectionBottomSheetOpen: Boolean = false,
    val isSettingsAlertOpen: Boolean = false,
    val isImportAlertOpen: Boolean = false,
    val selectedCardUUID : String = ""
)
