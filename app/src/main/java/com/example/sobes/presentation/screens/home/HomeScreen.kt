package com.example.sobes.presentation.screens.home

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.sobes.presentation.components.cards.Card
import com.example.sobes.presentation.components.text_field.SimpleTextField
import com.example.sobes.presentation.components.toolbars.MultiActionToolbar
import com.example.sobes.presentation.navigation.all_screens.navigateToAnswerCardsForSection
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
) {

    //TODO превьюю экрана не будет работать тк надо выносить в функцию где только ui без vm,мне лень)

    LaunchedEffect(Unit) {
        setBottomBarVisibility(true)
    }

    val viewModel: HomeScreenViewModel = hiltViewModel()
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getAllCards()
    }

    val pickJSONFileLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
            it?.let { uri ->
                viewModel.importDataFromJSON(uri)
            }
        }

    val isEnabled = remember(state.newSectionName) {
        derivedStateOf { state.newSectionName.isNotBlank() }
    }.value

    val addedNewSectionSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    if (state.isAddedNewSectionBottomSheetOpen) {
        SimpleBottomSheet(
            alertContent = {
                SimpleTextField(
                    modifier = Modifier.padding(bottom = 16.dp, start = 8.dp, end = 8.dp),
                    value = state.newSectionName,
                    onTextChange = remember { { viewModel.updateNewSectionName(it) } }
                )
                SimpleButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    buttonText = "Добавить",
                    isEnabled = isEnabled,
                    onClick = {
                        viewModel.createCard(state.newSectionName)
                        viewModel.closeSheet()
                    },
                )
            },
            sheetState = addedNewSectionSheetState,
            onDismissRequest = { viewModel.closeSheet() }
        )
    }

    if (state.isSettingsAlertOpen) {
        SimpleAlert(
            title = "Удалить раздел?",
            description = "При удалении раздела все данные раздела будут удалены навсегда",
            buttonText = "Удалить",
            onButtonClick = {
                viewModel.deleteSelectedCard(state.selectedCardUUID)
                viewModel.clearSelectedUUIDCard()
                viewModel.closeSettingsAlert()
            },
            onDismissRequest = {
                viewModel.clearSelectedUUIDCard()
                viewModel.closeSettingsAlert()
            }
        )
    }

    if (state.isImportAlertOpen) {
        SimpleAlert(
            title = "Импорт данных из резервной копии",
            description = "Все новые данные будут утеряны навсегда и заменены данными из резервной копии. Продолжить?",
            buttonText = "Да",
            onButtonClick = {
                pickJSONFileLauncher.launch("application/json")
                viewModel.closeImportAlert()
            },
            onDismissRequest = {
                viewModel.closeImportAlert()
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            MultiActionToolbar(
                titleId = R.string.sections_title,
                leftIconDrawable = R.drawable.ic_export,
                middleIconDrawable = R.drawable.ic_import,
                onMiddleBtnClick = { viewModel.openImportAlert() },
                onRightBtnClick = { viewModel.openSheet() },
                onLeftBtnClick = { viewModel.exportDataToJSON() },
                showMiddleIcon = true,
                showLeftIcon = true
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
                    .weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                itemsIndexed(state.listSections) { _, card ->
                    Card(
                        onCardClick = {
                            navController.navigateToAnswerCardsForSection(card.id) },
                        onMoreOptionsClick = {
                            viewModel.saveSelectedUUIDCard(card.id)
                            viewModel.openSettingsAlert()
                        },
                        name = card.name,
                        countCard = card.countCards.toString()
                    )
                }
            }
        }
    }
}