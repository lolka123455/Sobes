package com.example.sobes.presentation.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.navigation.compose.NavHost
import com.example.sobes.presentation.navigation.all_screens.answerCardsForSectionScreen
import com.example.sobes.presentation.navigation.bottom_nav_screens.homeScreen
import com.example.sobes.presentation.navigation.bottom_nav_screens.searchAllCardsScreen
import editAnswerCardScreen

@Composable
fun NavHost(
    appState: SobesAppState,
    modifier: Modifier = Modifier,
    startDestination: Screen = Screen.Home,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        homeScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
        )
        searchAllCardsScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
        )
        answerCardsForSectionScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
        )
        editAnswerCardScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
        )
    }
}