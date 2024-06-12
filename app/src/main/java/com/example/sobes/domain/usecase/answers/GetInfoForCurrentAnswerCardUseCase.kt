package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class GetInfoForCurrentAnswerCardUseCase @Inject constructor(
    private val answersRepository: AnswersRepository
) {
    suspend operator fun invoke(cardId: String): AnswerCard {
        return answersRepository.getInfoForCurrentAnswerCard(cardId)
    }
}