package com.example.sobes.presentation.screens.searchAllAnswerCardsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobes.data.di.IODispatcher
import com.example.sobes.domain.usecase.answers.GetAllListAnswersCardsUseCase
import com.example.sobes.domain.usecase.answers.SearchByNameAnswersCardsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SearchAllAnswerCardsViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val searchByNameAnswersCardsUseCase: SearchByNameAnswersCardsUseCase,
    private val getAllListAnswersCardsUseCase: GetAllListAnswersCardsUseCase
) : ViewModel(), ContainerHost<SearchAllAnswerCardsState, Nothing> {

    private val searchAnswerCardsFlow = MutableStateFlow("")

    override val container =
        container<SearchAllAnswerCardsState, Nothing>(SearchAllAnswerCardsState()) {
            getAllListAnswerCards()
            searching()
        }

    private fun getAllListAnswerCards() = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val answerCards = getAllListAnswersCardsUseCase.invoke()
                reduce {
                    state.copy(listAnswerCards = answerCards)
                }
            }.onSuccess {

            }.onFailure {

            }
        }

    }

    @OptIn(FlowPreview::class)
    private fun searching() = intent {
        viewModelScope.launch(ioDispatcher) {
            searchAnswerCardsFlow
                .debounce(200)
                .collectLatest { searchText ->
                    if (searchText.isBlank()) {
                        getAllListAnswerCards()
                    } else {
                        val filteredAnswerCards = searchByNameAnswersCardsUseCase.invoke(searchText)
                        reduce {
                            state.copy(listAnswerCards = filteredAnswerCards)
                        }
                    }
                }
        }
    }

    fun search(searchText: String) = blockingIntent {
        reduce {
            state.copy(searchText = searchText)
        }
        searchAnswerCardsFlow.value = searchText
    }

    fun onClear() = blockingIntent {
        reduce { state.copy(searchText = "") }
        searchAnswerCardsFlow.value = ""
    }
}