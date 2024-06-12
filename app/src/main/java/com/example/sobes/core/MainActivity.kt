package com.example.sobes.core

import SobesApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.example.sobes.presentation.color.SobesThemeObj
import com.example.sobes.presentation.navigation.core.SobesAppState
import com.example.sobes.presentation.navigation.core.rememberAppState
import com.example.sobes.ui.theme.SobesTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterialNavigationApi::class)
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            SobesTheme {
                val bottomSheetNavigator = rememberBottomSheetNavigator()
                val navController = rememberNavController(bottomSheetNavigator)
                val appState: SobesAppState = rememberAppState(navController = navController)
                Box(modifier = Modifier.background(color = SobesThemeObj.colors.backgroundDarkAppearance)) {
                    SobesApp(
                        appState = appState,
                    )
                }
            }
        }
    }
}