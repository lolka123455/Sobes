package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class GetAllListAnswerCardsBySectionIdUseCase @Inject constructor(
    private val answersRepository: AnswersRepository
) {
    suspend operator fun invoke(sectionId: String): List<AnswerCard> {
        return answersRepository.getAllListAnswerCardsBySectionId(sectionId)
    }
}