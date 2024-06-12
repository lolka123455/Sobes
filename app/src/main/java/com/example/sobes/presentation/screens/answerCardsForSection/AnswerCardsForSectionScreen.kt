package com.example.sobes.presentation.screens.answerCardsForSection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sobes.R
import com.example.sobes.presentation.components.SearchView
import com.example.sobes.presentation.components.alert.SimpleAlert
import com.example.sobes.presentation.components.bottom_sheet.SimpleBottomSheet
import com.example.sobes.presentation.components.buttons.SimpleButton
import com.example.sobes.presentation.components.cards.AnswerCardBase
import com.example.sobes.presentation.components.text_field.SimpleTextField
import com.example.sobes.presentation.components.toolbars.MultiActionToolbar
import navigateToEditAnswerCard
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerCardsForSection(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
    sectionId: String
) {

    //TODO превьюю экрана не будет работать тк надо выносить в функцию где только ui без vm,мне лень)

    LaunchedEffect(Unit) {
        setBottomBarVisibility(true)
    }

    val viewModel: AnswerCardsForSectionViewModel = hiltViewModel()
    val state = viewModel.collectAsState().value

    val isEnabled = remember(state.newAnswerCardName) {
        derivedStateOf { state.newAnswerCardName.isNotBlank() }
    }.value

    val addedNewAnswerCardSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(Unit) {
        viewModel.getAllListAnswerCardsBySectionId(sectionId)
    }

    if (state.isAddedNewAnswerCardBottomSheetOpen) {
        SimpleBottomSheet(
            alertContent = {
                SimpleTextField(
                    modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
                    value = state.newAnswerCardName,
                    onTextChange = remember { { viewModel.updateAnswerCardName(it) } }
                )
                Spacer(modifier = Modifier.height(8.dp))
                SimpleTextField(
                    modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
                    value = state.newAnswerCardDescription,
                    onTextChange = remember { { viewModel.updateNewAnswerCardDescription(it) } }
                )
                SimpleButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    buttonText = "Добавить",
                    isEnabled = isEnabled,
                    onClick = {
                        viewModel.createAnswerCard(
                            titleCard = state.newAnswerCardName,
                            descriptionAnswer = state.newAnswerCardDescription,
                            sectionId = sectionId
                        )
                        viewModel.closeSheet()
                    },
                )
            },
            sheetState = addedNewAnswerCardSheetState,
            onDismissRequest = { viewModel.closeSheet() }
        )
    }

    if (state.isSettingsAlertOpen) {
        SimpleAlert(
            title = "Удалить карточку?",
            description = "При удалении карточки все данные карточки будут удалены навсегда",
            buttonText = "Удалить",
            onButtonClick = {
                viewModel.deleteSelectedAnswerCard(state.selectedAnswerCardUUID,sectionId)
                viewModel.clearSelectedUUIDCard()
                viewModel.closeAlert()
            },
            onDismissRequest = {
                viewModel.clearSelectedUUIDCard()
                viewModel.closeAlert()
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            MultiActionToolbar(
                titleId = R.string.answers_title,
                onLeftBtnClick = navController::popBackStack,
                onRightBtnClick = { viewModel.openSheet() },
                showMiddleIcon = false
            )

            SearchView(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                ),
                placeHolderText = "Поиск по карточкам",
                searchText = state.searchText,
                onSearch = remember { { viewModel.search(it) } },
                onClear = remember { { viewModel.onClear() } }
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 100.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(state.listAnswerCards) {  _, card ->
                    AnswerCardBase(
                        onCardClick = {
                            navController.navigateToEditAnswerCard(
                                card.id,
                                true
                            )
                        },
                        onMoreOptionsClick = {
                            viewModel.saveSelectedUUIDCard(card.id)
                            viewModel.openAlert()
                        },
                        name = card.titleCard,
                        descriptionCard = card.descriptionAnswer ?: ""
                    )
                }
            }
        }
    }
}
