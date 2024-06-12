package com.example.sobes.presentation.screens.home

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sobes.data.di.IODispatcher
import com.example.sobes.data.di.MainDispatcher
import com.example.sobes.domain.usecase.json.ExportDataUseCase
import com.example.sobes.domain.usecase.json.ImportDataUseCase
import com.example.sobes.domain.usecase.sections.CreateNewSectionsUseCase
import com.example.sobes.domain.usecase.sections.DeleteSelectedSectionUseCase
import com.example.sobes.domain.usecase.sections.GetAllListSectionsUseCase
import com.example.sobes.domain.usecase.sections.SearchByNameSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
class HomeScreenViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val createNewSectionsUseCase: CreateNewSectionsUseCase,
    private val deleteSelectedSectionUseCase: DeleteSelectedSectionUseCase,
    private val getAllListSectionsUseCase: GetAllListSectionsUseCase,
    private val searchByNameSectionsUseCase: SearchByNameSectionsUseCase,
    private val exportDataUseCase: ExportDataUseCase,
    private val importDataUseCase: ImportDataUseCase,
    @ApplicationContext private val context: Context,
) : ViewModel(), ContainerHost<HomeScreenState, Nothing> {

    private val searchCardsFlow = MutableStateFlow("")

    override val container = container<HomeScreenState, Nothing>(HomeScreenState()) {
        searching()
    }

    fun getAllCards() = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                getAllListSectionsUseCase.invoke()
            }.onSuccess { sections ->
                reduce {
                    state.copy(listSections = sections)
                }
            }.onFailure { throwable ->

            }
        }
    }

    fun createCard(cardName: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                createNewSectionsUseCase.invoke(cardName)
            }.onSuccess {
                getAllCards()
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun searching() = intent {
        viewModelScope.launch(ioDispatcher) {
            searchCardsFlow
                .debounce(200)
                .collectLatest { searchText ->
                    if (searchText.isBlank()) {
                        getAllCards()
                    } else {
                        val filteredSections = searchByNameSectionsUseCase.invoke(searchText)
                        reduce {
                            state.copy(listSections = filteredSections)
                        }
                    }
                }
        }
    }

    fun search(searchText: String) = blockingIntent {
        reduce {
            state.copy(searchText = searchText)
        }
        searchCardsFlow.value = searchText
    }

    fun onClear() = blockingIntent {
        reduce { state.copy(searchText = "") }
        searchCardsFlow.value = ""
    }

    fun openSheet() = intent {
        reduce { state.copy(isAddedNewSectionBottomSheetOpen = true) }
    }

    fun closeSheet() = intent {
        reduce {
            state.copy(isAddedNewSectionBottomSheetOpen = false)
        }
        reduce {
            state.copy(newSectionName = "")
        }
    }

    fun openSettingsAlert() = intent {
        reduce { state.copy(isSettingsAlertOpen = true) }
    }

    fun closeSettingsAlert() = intent {
        reduce { state.copy(isSettingsAlertOpen = false) }
    }

    fun openImportAlert() = intent {
        reduce { state.copy(isImportAlertOpen = true) }
    }

    fun closeImportAlert() = intent {
        reduce { state.copy(isImportAlertOpen = false) }
    }

    fun updateNewSectionName(newName: String) = blockingIntent {
        reduce {
            state.copy(newSectionName = newName)
        }
    }

    fun saveSelectedUUIDCard(uuid: String) = intent {
        reduce {
            state.copy(selectedCardUUID = uuid)
        }
    }

    fun clearSelectedUUIDCard() = intent {
        reduce {
            state.copy(selectedCardUUID = "")
        }
    }

    fun exportDataToJSON() {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                exportDataUseCase.execute()
            }.onSuccess {
                viewModelScope.launch(mainDispatcher) {
                    Toast.makeText(context,"Сохранено успешно",Toast.LENGTH_SHORT).show()
                }
            }.onFailure {
                viewModelScope.launch(mainDispatcher) {
                    Toast.makeText(context,"Ошибка сохранения",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun importDataFromJSON(file: Uri) = intent {
        viewModelScope.launch(ioDispatcher){
            runCatching {
                importDataUseCase.execute(file)
            }.onSuccess {
                getAllCards()
                viewModelScope.launch(mainDispatcher) {
                    Toast.makeText(context,"Карточки успешно импортированы",Toast.LENGTH_SHORT).show()
                }
            }.onFailure {
                viewModelScope.launch(mainDispatcher) {
                    Toast.makeText(context,"Ошибка при импорте,конфликт при вставки в БД",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun deleteSelectedCard(uuid: String) = intent {
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                deleteSelectedSectionUseCase.invoke(uuid)
            }.onSuccess {
                getAllCards()
            }.onFailure { throwable ->

            }
        }
    }

}