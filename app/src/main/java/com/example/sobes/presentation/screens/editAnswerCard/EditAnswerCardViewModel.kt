package com.example.sobes.presentation.screens.editAnswerCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobes.data.di.IODispatcher
import com.example.sobes.domain.usecase.answers.DeleteSelectedAnswerCardUseCase
import com.example.sobes.domain.usecase.answers.EditCurrentAnswerCardUseCase
import com.example.sobes.domain.usecase.answers.GetInfoForCurrentAnswerCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EditAnswerCardViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val deleteSelectedAnswerCardUseCase: DeleteSelectedAnswerCardUseCase,
    private val editCurrentAnswerCardUseCase: EditCurrentAnswerCardUseCase,
    private val getInfoForCurrentAnswerCardUseCase: GetInfoForCurrentAnswerCardUseCase
) : ViewModel(), ContainerHost<EditAnswerCardState, Nothing> {

    override val container =
        container<EditAnswerCardState, Nothing>(EditAnswerCardState()) {
        }

    fun getFullInfoCurrentAnswerCard(cardId: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                getInfoForCurrentAnswerCardUseCase(cardId)
            }.onSuccess { answerCard ->
                reduce {
                    state.copy(
                        title = answerCard.titleCard,
                        description = answerCard.descriptionAnswer
                    )
                }
            }.onFailure {

            }
        }
    }

    fun editCurrentAnswerCard(cardId: String, title: String?, description: String?) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                editCurrentAnswerCardUseCase(cardId, title, description)
            }.onSuccess {

            }.onFailure {

            }
        }
    }

    fun changeModeScreen(mode: Boolean) = intent {
        if (mode) {
            reduce {
                state.copy(isNormalMode = true)
            }
        } else {
            reduce {
                state.copy(isNormalMode = false)
            }
        }
    }

    fun deleteSelectedAnswerCard(cardId: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                deleteSelectedAnswerCardUseCase(cardId)
            }.onSuccess {

            }.onFailure {

            }
        }
    }

    fun updateAnswerCardName(newName: String) = blockingIntent {
        reduce {
            state.copy(title = newName)
        }
    }

    fun updateAnswerCardDescription(newDescription: String) = blockingIntent {
        reduce {
            state.copy(description = newDescription)
        }
    }

    fun openAlert() = intent {
        reduce { state.copy(isDeleteAlertOpen = true) }
    }

    fun closeAlert() = intent {
        reduce { state.copy(isDeleteAlertOpen = false) }
    }
}