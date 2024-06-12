package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class EditCurrentAnswerCardUseCase @Inject constructor(
    private val answersRepository: AnswersRepository
) {
    suspend operator fun invoke(cardId: String, title: String?, description: String?) {
        answersRepository.editCurrentAnswerCard(cardId, title, description)
    }
}