package com.example.sobes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.sobes.data.database.entity.AnswerCardEntity

@Dao
interface AnswerCardDao {

    companion object {
        const val ANSWER_CARD_TABLE = "answer_card"
    }

    @Query("SELECT * FROM $ANSWER_CARD_TABLE")
    suspend fun getAllAnswerCards(): List<AnswerCardEntity>

    @Query(
        """
        SELECT * FROM $ANSWER_CARD_TABLE 
        WHERE titleCard LIKE :searchText OR descriptionAnswer LIKE :searchText
        ORDER BY 
            CASE 
                WHEN titleCard LIKE :searchText THEN 1 
                ELSE 2 
            END
    """
    )
    suspend fun searchAnswerCardsByMatchWord(searchText: String): List<AnswerCardEntity>

    @Query("SELECT * FROM $ANSWER_CARD_TABLE WHERE sectionId = :sectionId")
    suspend fun getAnswerCardsBySectionId(sectionId: String): List<AnswerCardEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun createAnswerCard(answerCard: AnswerCardEntity)

    @Query("DELETE FROM $ANSWER_CARD_TABLE WHERE id = :answerCardId")
    suspend fun deleteAnswerCardById(answerCardId: String)

    @Query("UPDATE $ANSWER_CARD_TABLE SET titleCard = :title, descriptionAnswer = :description WHERE id = :cardId")
    suspend fun updateAnswerCard(cardId: String, title: String?, description: String?)

    @Query("SELECT * FROM $ANSWER_CARD_TABLE WHERE id = :cardId")
    suspend fun getAnswerCardById(cardId: String): AnswerCardEntity

    @Transaction
    suspend fun createAnswerCardAndUpdateCount(answerCard: AnswerCardEntity, cardInfoDao: CardInfoDao) {
        createAnswerCard(answerCard)
        cardInfoDao.updateCountCards(answerCard.sectionId)
    }

    @Transaction
    suspend fun deleteAnswerCardAndUpdateCount(answerCardId: String, sectionId: String, cardInfoDao: CardInfoDao) {
        deleteAnswerCardById(answerCardId)
        cardInfoDao.updateCountCards(sectionId)
    }

    @Transaction
    suspend fun updateAnswerCardAndUpdateCount(cardId: String, title: String?, description: String?, sectionId: String, cardInfoDao: CardInfoDao) {
        updateAnswerCard(cardId, title, description)
        cardInfoDao.updateCountCards(sectionId)
    }
}