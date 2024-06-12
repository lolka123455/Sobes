import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.sobes.presentation.navigation.core.Screen
import com.example.sobes.presentation.screens.editAnswerCard.EditAnswerCardScreen

private const val TRANSITION_DURATION = 300

fun NavController.navigateToEditAnswerCard(cardId: String, isNormalMode: Boolean) {
    this.navigate(Screen.EditAnswerCard(cardId, isNormalMode))
}

fun NavGraphBuilder.editAnswerCardScreen(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
) {
    composable<Screen.EditAnswerCard>(
        enterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
        exitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popExitTransition = { fadeOut(tween(TRANSITION_DURATION)) },
        popEnterTransition = { fadeIn(tween(TRANSITION_DURATION)) },
    ) { backStackEntry ->
        val screen = backStackEntry.toRoute<Screen.EditAnswerCard>()
        EditAnswerCardScreen(
            navController = navController,
            setBottomBarVisibility = setBottomBarVisibility,
            bottomBarHeight = bottomBarHeight,
            cardId = screen.cardId,
            isNormalMode = screen.isNormalMode
        )
    }
}