package com.example.sobes.presentation.screens.searchAllAnswerCardsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sobes.R
import com.example.sobes.presentation.components.SearchView
import com.example.sobes.presentation.components.cards.AnswerCardBase
import com.example.sobes.presentation.components.toolbars.MultiActionToolbar
import navigateToEditAnswerCard
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun SearchAllAnswerCardsScreen(
    navController: NavController,
    setBottomBarVisibility: (Boolean) -> Unit,
    bottomBarHeight: Dp,
) {

    //TODO превьюю экрана не будет работать тк надо выносить в функцию где только ui без vm,мне лень)

    LaunchedEffect(Unit) {
        setBottomBarVisibility(true)
    }

    val viewModel: SearchAllAnswerCardsViewModel = hiltViewModel()
    val state = viewModel.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            MultiActionToolbar(
                titleId = R.string.search_title,
                showMiddleIcon = false,
                showLeftIcon = false,
                showRightIcon = false
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
                itemsIndexed(state.listAnswerCards) { _, card ->
                    AnswerCardBase(
                        onCardClick = {
                            navController.navigateToEditAnswerCard(
                                card.id,
                                false
                            )
                        },
                        isShowSettingsIcon = false,
                        onMoreOptionsClick = {},
                        name = card.titleCard,
                        descriptionCard = card.descriptionAnswer ?: ""
                    )
                }
            }
        }
    }
}