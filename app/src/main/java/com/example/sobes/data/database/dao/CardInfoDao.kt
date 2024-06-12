package com.example.sobes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sobes.data.database.entity.CardInfoEntity

@Dao
interface CardInfoDao {

    companion object {
        const val CARD_INFO_TABLE = "card_info"
    }

    @Query("SELECT * FROM $CARD_INFO_TABLE")
    suspend fun getAllSections(): List<CardInfoEntity>

    @Query("SELECT * FROM $CARD_INFO_TABLE WHERE name LIKE :searchText")
    suspend fun searchSectionsByName(searchText: String): List<CardInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSection(section: CardInfoEntity)

    @Query("DELETE FROM $CARD_INFO_TABLE WHERE id = :sectionId")
    suspend fun deleteSectionById(sectionId: String)

    @Query("UPDATE $CARD_INFO_TABLE SET countCards = (SELECT COUNT(*) FROM ${AnswerCardDao.ANSWER_CARD_TABLE} WHERE sectionId = :sectionId) WHERE id = :sectionId")
    suspend fun updateCountCards(sectionId: String)
}
