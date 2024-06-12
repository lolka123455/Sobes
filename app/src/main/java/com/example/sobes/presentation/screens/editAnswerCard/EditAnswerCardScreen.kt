package com.example.sobes.presentation.screens.editAnswerCard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sobes.R
import com.example.sobes.presentation.components.alert.SimpleAlert
import com.example.sobes.presentation.components.text_field.DescriptionField
import com.example.sobes.presentation.components.text_field.SimpleTextField
import com.example.sobes.presentation.components.toolbars.MultiActionToolbar
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun EditAnswerCardScreen(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
    cardId: String,
    isNormalMode: Boolean
) {

    //TODO превьюю экрана не будет работать тк надо выносить в функцию где только ui без vm,мне лень)

    LaunchedEffect(Unit) {
        setBottomBarVisibility(true)
    }

    val viewModel: EditAnswerCardViewModel = hiltViewModel()
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.changeModeScreen(isNormalMode)
        viewModel.getFullInfoCurrentAnswerCard(cardId)
    }

    if (state.isDeleteAlertOpen) {
        SimpleAlert(
            title = "Удалить карточку?",
            description = "При удалении карточки все данные карточки будут удалены навсегда",
            buttonText = "Удалить",
            onButtonClick = {
                viewModel.deleteSelectedAnswerCard(cardId)
                viewModel.closeAlert()
                navController.popBackStack()
            },
            onDismissRequest = {
                viewModel.closeAlert()
            }
        )
    }

    if (state.isNormalMode) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                MultiActionToolbar(
                    titleId = R.string.edit_title,
                    onLeftBtnClick = navController::popBackStack,
                    rightIconDrawable = R.drawable.ic_ok,
                    middleIconDrawable = R.drawable.ic_trash,
                    onRightBtnClick = {
                        viewModel.editCurrentAnswerCard(
                            cardId = cardId,
                            title = state.title,
                            description = state.description
                        )
                        navController.popBackStack()
                    },
                    onMiddleBtnClick = {
                        viewModel.openAlert()
                    },
                    showMiddleIcon = true
                )
                SimpleTextField(
                    modifier = Modifier.padding(bottom = 16.dp),
                    value = state.title ?: "",
                    onTextChange = remember { { viewModel.updateAnswerCardName(it) } }
                )
                Spacer(modifier = Modifier.height(8.dp))

                DescriptionField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    title = R.string.answer,
                    description = state.description ?: "",
                    onTextChanged = remember { { viewModel.updateAnswerCardDescription(it) } }
                )
            }

        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                MultiActionToolbar(
                    titleId = R.string.app_name,
                    onLeftBtnClick = navController::popBackStack,
                    showMiddleIcon = false
                )
                SimpleTextField(
                    modifier = Modifier.padding(bottom = 16.dp),
                    value = state.title ?: "",
                    isReadOnly = true,
                    onTextChange = remember { { viewModel.updateAnswerCardName(it) } }
                )
                Spacer(modifier = Modifier.height(8.dp))

                DescriptionField(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    title = R.string.answer,
                    isReadOnly = true,
                    description = state.description ?: "",
                    onTextChanged = remember { { viewModel.updateAnswerCardDescription(it) } }
                )
            }
        }
    }
}