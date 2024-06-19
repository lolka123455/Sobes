import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sobes.presentation.color.SobesThemeObj
import com.example.sobes.presentation.navigation.core.Icon
import com.example.sobes.presentation.navigation.core.NavHost
import com.example.sobes.presentation.navigation.core.Screen
import com.example.sobes.presentation.navigation.core.SobesAppState
import com.example.sobes.presentation.snack.DefaultSnackBarHost

@Composable
fun SobesApp(
    appState: SobesAppState,
) {
    val snackHostState = remember { SnackbarHostState() }
    val isBottomBarVisible = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        contentColor = SobesThemeObj.colors.mainBlack,
        snackbarHost = {
            DefaultSnackBarHost(
                snackBarHostState = snackHostState,
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(horizontal = 8.dp)
                    .padding(top = 4.dp)
                    .wrapContentHeight(Alignment.Bottom)
            )
        },
        bottomBar = {
            Box {
                AnimatedVisibility(
                    visible = isBottomBarVisible.value,
                    enter = slideInVertically { it } + fadeIn(),
                    exit = slideOutVertically { it } + fadeOut(),
                ) {
                    BottomBar(
                        destinations = listOf(Screen.Home, Screen.SearchAllCards),
                        appState = appState,
                        modifier = Modifier.background(SobesThemeObj.colors.white),
                    )
                }
            }
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()) // Используем только нижний отступ
        ) {
            NavHost(
                appState = appState,
                startDestination = Screen.Home,
                setBottomBarVisibility = { isBottomBarVisible.value = it },
                bottomBarHeight = paddingValues.calculateBottomPadding(),
            )
        }
    }
}

@Composable
private fun BottomBar(
    destinations: List<Screen>,
    appState: SobesAppState,
    modifier: Modifier = Modifier,
) {
    val selectedDestination = remember { mutableStateOf(destinations.first()) }

    Column {
        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxSize(),
            color = SobesThemeObj.colors.gray2DarkAppearance
        )
        MainNavigationBar(
            modifier = modifier.heightIn(min = 50.dp),
        ) {
            destinations.forEach { destination ->
                val selected = selectedDestination.value == destination

                NavigationBarItem(
                    modifier = Modifier.heightIn(min = 60.dp),
                    selected = selected,
                    onClick = {
                        selectedDestination.value = destination
                        appState.navigateToBottomScreens(destination)
                    },
                    icon = {
                        val icon = if (selected) {
                            when (destination) {
                                is Screen.Home -> destination.selectedIcon
                                is Screen.SearchAllCards -> destination.selectedIcon
                                else -> null
                            }
                        } else {
                            when (destination) {
                                is Screen.Home -> destination.unselectedIcon
                                is Screen.SearchAllCards -> destination.unselectedIcon
                                else -> null
                            }
                        }

                        when (icon) {
                            is Icon.DrawableResourceIcon -> Icon(
                                painter = painterResource(id = icon.id),
                                contentDescription = null,
                            )
                        }
                    },
                    label = {
                        val textId = when (destination) {
                            is Screen.Home -> destination.iconTextId
                            is Screen.SearchAllCards -> destination.iconTextId
                            else -> null
                        }

                        textId?.let {
                            Text(
                                text = stringResource(it),
                                style = SobesThemeObj.typography.caption2,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedTextColor = SobesThemeObj.colors.greenDarkAppearance,
                        unselectedTextColor = SobesThemeObj.colors.white.copy(alpha = 0.5f),
                    )
                )
            }
        }
    }
}

@Composable
fun MainNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier = modifier,
        contentColor = SobesThemeObj.colors.darkBlue,
        tonalElevation = 0.dp,
        content = content,
        containerColor = SobesThemeObj.colors.green
    )
}