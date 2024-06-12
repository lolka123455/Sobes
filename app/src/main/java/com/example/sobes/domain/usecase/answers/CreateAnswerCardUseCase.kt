package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class CreateAnswerCardUseCase @Inject constructor(
    private val answersRepository: AnswersRepository
) {
    suspend operator fun invoke(answerCard: AnswerCard) {
        answersRepository.createAnswerCard(answerCard)
    }
}