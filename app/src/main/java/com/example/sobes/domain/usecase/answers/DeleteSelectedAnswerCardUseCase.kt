package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class DeleteSelectedAnswerCardUseCase @Inject constructor(
    private val answersRepository: AnswersRepository
) {
    suspend operator fun invoke(cardId: String) {
        answersRepository.deleteSelectedAnswerCard(cardId)
    }
}