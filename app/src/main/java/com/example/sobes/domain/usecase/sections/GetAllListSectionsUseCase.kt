package com.example.sobes.domain.usecase.sections

import com.example.sobes.domain.model.CardInfo
import com.example.sobes.domain.repository.SectionRepository
import javax.inject.Inject

class GetAllListSectionsUseCase @Inject constructor(
    private val createNewSectionsRepository: SectionRepository,
) {

    suspend fun invoke(): List<CardInfo> {
        return createNewSectionsRepository.getAllListSections()
    }

}