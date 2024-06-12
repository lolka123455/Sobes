package com.example.sobes.domain.usecase.sections

import com.example.sobes.domain.repository.SectionRepository
import javax.inject.Inject

class CreateNewSectionsUseCase @Inject constructor(
    private val createNewSectionsRepository: SectionRepository,
) {
    suspend fun invoke(sectionName: String) {
        createNewSectionsRepository.createNewSection(sectionName)
    }
}