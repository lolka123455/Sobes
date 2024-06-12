package com.example.sobes.domain.usecase.sections

import com.example.sobes.domain.model.CardInfo
import com.example.sobes.domain.repository.SectionRepository
import javax.inject.Inject

class SearchByNameSectionsUseCase @Inject constructor(
    private val createNewSectionsRepository: SectionRepository,
) {

    suspend fun invoke(searchText: String): List<CardInfo> {
        return createNewSectionsRepository.searchByNameSections(searchText)
    }

}