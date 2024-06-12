package com.example.sobes.data.repository

import com.example.sobes.data.database.AppDatabase
import com.example.sobes.data.mapper.AnswerCardMapper
import com.example.sobes.domain.model.AnswerCard
import com.example.sobes.domain.repository.AnswersRepository
import java.util.UUID
import javax.inject.Inject

class AnswersRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val answerCardMapper: AnswerCardMapper
) : AnswersRepository {

    override suspend fun getAllListAnswersCards(): List<AnswerCard> {
        return database.answerCardDao().getAllAnswerCards()
            .map { answerCardMapper.fromEntityToDomain(it) }
    }

    override suspend fun searchByNameAnswersCards(searchText: String): List<AnswerCard> {
        return database.answerCardDao().searchAnswerCardsByName("%$searchText%")
            .map { answerCardMapper.fromEntityToDomain(it) }
    }

    override suspend fun getAllListAnswerCardsBySectionId(sectionId: String): List<AnswerCard> {
        return database.answerCardDao().getAnswerCardsBySectionId(sectionId)
            .map { answerCardMapper.fromEntityToDomain(it) }
    }

    override suspend fun createAnswerCard(answerCard: AnswerCard) {
        val id = UUID.randomUUID().toString()
        val entity = answerCardMapper.fromDomainToEntity(answerCard.copy(id = id))
        database.answerCardDao().createAnswerCardAndUpdateCount(entity, database.cardInfoDao())
    }

    override suspend fun deleteSelectedAnswerCard(cardId: String) {
        val sectionId = database.answerCardDao().getAnswerCardById(cardId).sectionId
        database.answerCardDao().deleteAnswerCardAndUpdateCount(cardId, sectionId, database.cardInfoDao())
    }

    override suspend fun editCurrentAnswerCard(
        cardId: String,
        title: String?,
        description: String?
    ) {
        val currentCard = database.answerCardDao().getAnswerCardById(cardId)
        val updatedTitle = title ?: currentCard.titleCard
        val updatedDescription = description ?: currentCard.descriptionAnswer
        database.answerCardDao().updateAnswerCardAndUpdateCount(cardId, updatedTitle, updatedDescription, currentCard.sectionId, database.cardInfoDao())
    }

    override suspend fun getInfoForCurrentAnswerCard(cardId: String): AnswerCard {
        val entity = database.answerCardDao().getAnswerCardById(cardId)
        return answerCardMapper.fromEntityToDomain(entity)
    }
}