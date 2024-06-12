package com.example.sobes.presentation.screens.answerCardsForSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobes.data.di.IODispatcher
import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.usecase.answers.CreateAnswerCardUseCase
import com.example.sobes.domain.usecase.answers.DeleteSelectedAnswerCardUseCase
import com.example.sobes.domain.usecase.answers.GetAllListAnswerCardsBySectionIdUseCase
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
class AnswerCardsForSectionViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val createAnswerCardUseCase: CreateAnswerCardUseCase,
    private val deleteSelectedAnswerCardUseCase: DeleteSelectedAnswerCardUseCase,
    private val getAllListAnswerCardsBySectionIdUseCase: GetAllListAnswerCardsBySectionIdUseCase,
    private val searchByNameAnswersCardsUseCase: SearchByNameAnswersCardsUseCase
) : ViewModel(), ContainerHost<AnswerCardsForSectionState, Nothing> {

    private val searchAnswerCardsFlow = MutableStateFlow("")

    override val container =
        container<AnswerCardsForSectionState, Nothing>(AnswerCardsForSectionState()) {
            searching()
        }

    fun getAllListAnswerCardsBySectionId(sectionId: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val answerCards = getAllListAnswerCardsBySectionIdUseCase(sectionId)
                reduce {
                    state.copy(listAnswerCards = answerCards)
                }
                reduce {
                    state.copy(sectionId = sectionId)
                }
            }.onSuccess {

            }.onFailure {

            }
        }
    }

    fun createAnswerCard(titleCard: String, sectionId: String, descriptionAnswer: String? = null) = intent {
        viewModelScope.launch(ioDispatcher) {
            val newAnswerCard = AnswerCard(
                id = "",
                titleCard = titleCard,
                descriptionAnswer = descriptionAnswer,
                sectionId = sectionId
            )
            createAnswerCardUseCase(newAnswerCard)
            runCatching {
                val answerCards = getAllListAnswerCardsBySectionIdUseCase(sectionId)
                reduce {
                    state.copy(listAnswerCards = answerCards)
                }
            }.onSuccess {
                getAllListAnswerCardsBySectionId(state.sectionId)
            }.onFailure {

            }
        }
    }

    fun deleteSelectedAnswerCard(cardId: String, sectionId: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            deleteSelectedAnswerCardUseCase(cardId)
            runCatching {
                val answerCards = getAllListAnswerCardsBySectionIdUseCase(sectionId)
                reduce {
                    state.copy(listAnswerCards = answerCards)
                }
            }.onSuccess {
                getAllListAnswerCardsBySectionId(state.sectionId)
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
                        getAllListAnswerCardsBySectionId(state.sectionId)
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

    fun openSheet() = intent {
        reduce { state.copy(isAddedNewAnswerCardBottomSheetOpen = true) }
    }

    fun closeSheet() = intent {
        reduce {
            state.copy(isAddedNewAnswerCardBottomSheetOpen = false)
        }
        reduce {
            state.copy(newAnswerCardName = "")
        }
        reduce {
            state.copy(newAnswerCardDescription = "")
        }
    }

    fun openAlert() = intent {
        reduce { state.copy(isSettingsAlertOpen = true) }
    }

    fun closeAlert() = intent {
        reduce { state.copy(isSettingsAlertOpen = false) }
    }

    fun saveSelectedUUIDCard(uuid: String) = intent {
        reduce {
            state.copy(selectedAnswerCardUUID = uuid)
        }
    }

    fun clearSelectedUUIDCard() = intent {
        reduce {
            state.copy(selectedAnswerCardUUID = "")
        }
    }

    fun updateAnswerCardName(newName: String) = blockingIntent {
        reduce {
            state.copy(newAnswerCardName = newName)
        }
    }

    fun updateNewAnswerCardDescription(newName: String) = blockingIntent {
        reduce {
            state.copy(newAnswerCardDescription = newName)
        }
    }

}