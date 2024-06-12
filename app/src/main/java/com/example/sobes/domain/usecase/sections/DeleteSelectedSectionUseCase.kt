package com.example.sobes.domain.usecase.sections

import com.example.sobes.domain.repository.SectionRepository
import javax.inject.Inject

class DeleteSelectedSectionUseCase @Inject constructor(
    private val createNewSectionsRepository: SectionRepository,
) {

    suspend fun invoke(sectionName: String) {
        createNewSectionsRepository.deleteSelectedSection(sectionName)
    }
}