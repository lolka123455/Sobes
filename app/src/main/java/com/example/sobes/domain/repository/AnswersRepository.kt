package com.example.sobes.domain.repository

import com.example.sobes.domain.model.AnswerCard

interface AnswersRepository {

    suspend fun getAllListAnswersCards(): List<AnswerCard>

    suspend fun searchByNameAnswersCards(searchText: String): List<AnswerCard>

    suspend fun getAllListAnswerCardsBySectionId(sectionId: String): List<AnswerCard>

    suspend fun createAnswerCard(answerCard: AnswerCard)

    suspend fun deleteSelectedAnswerCard(cardId: String)

    suspend fun editCurrentAnswerCard(cardId: String, title: String?, description: String?)

    suspend fun getInfoForCurrentAnswerCard(cardId : String): AnswerCard

}