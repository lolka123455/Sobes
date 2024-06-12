package com.example.sobes.presentation.navigation.core

import com.example.sobes.R
import kotlinx.serialization.Serializable
import com.example.sobes.presentation.navigation.core.Icon.DrawableResourceIcon

sealed interface Screen {
    @Serializable
    data object Home : Screen {
        val selectedIcon = DrawableResourceIcon(SobesIcons.BaseScreenIconSelected)
        val unselectedIcon = DrawableResourceIcon(SobesIcons.BaseScreenIconUnselected)
        val iconTextId = R.string.tab_bar_home_screen_title
    }

    @Serializable
    data object SearchAllCards : Screen {
        val selectedIcon = DrawableResourceIcon(SobesIcons.SearchScreenIconSelected)
        val unselectedIcon = DrawableResourceIcon(SobesIcons.SearchScreenIconUnselected)
        val iconTextId = R.string.tab_bar_search_all_cards_title
    }

    @Serializable
    data class AnswerCardsForSection(val sectionId: String) : Screen

    @Serializable
    data class EditAnswerCard(val cardId: String, val isNormalMode: Boolean) : Screen
}