package com.example.sobes.presentation.navigation.core

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.sobes.presentation.navigation.bottom_nav_screens.navigateToHome
import com.example.sobes.presentation.navigation.bottom_nav_screens.navigateToSearchAllCards
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): SobesAppState {
    return remember(
        navController,
        coroutineScope,
    ) {
        SobesAppState(
            navController,
        )
    }
}

class SobesAppState(
    val navController: NavHostController,
) {

    val screens: List<Screen> = listOf(
        Screen.Home,
        Screen.SearchAllCards
    )

    fun navigateToBottomScreens(screen: Screen) {
        when (screen) {
            is Screen.Home -> navController.navigateToHome()
            is Screen.SearchAllCards -> navController.navigateToSearchAllCards()
            else -> Log.d("NavigationDebug", "Unknown screen: ${screen::class.simpleName}")
        }
    }
}