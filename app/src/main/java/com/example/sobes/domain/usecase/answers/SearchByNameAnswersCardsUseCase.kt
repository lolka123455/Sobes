package com.example.sobes.domain.usecase.answers

import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.repository.AnswersRepository
import javax.inject.Inject

class SearchByNameAnswersCardsUseCase @Inject constructor(
    private val answersRepository: AnswersRepository,
) {

    suspend fun invoke(searchText: String): List<AnswerCard> {
        return answersRepository.searchByNameAnswersCards(searchText)
    }

}