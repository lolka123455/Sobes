package com.example.sobes.presentation.navigation.bottom_nav_screens

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.sobes.presentation.navigation.core.Screen
import com.example.sobes.presentation.screens.searchAllAnswerCardsScreen.SearchAllAnswerCardsScreen

private const val TRANSITION_DURATION = 300

fun NavController.navigateToSearchAllCards() {
    this.navigate(Screen.SearchAllCards)
}

fun NavGraphBuilder.searchAllCardsScreen(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
) {
    composable<Screen.SearchAllCards>(
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) {
        SearchAllAnswerCardsScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
        )
    }
}