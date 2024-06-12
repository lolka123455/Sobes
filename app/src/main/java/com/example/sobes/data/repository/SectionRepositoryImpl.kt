package com.example.sobes.data.repository

import com.example.sobes.data.database.AppDatabase
import com.example.sobes.data.database.entity.CardInfoEntity
import com.example.sobes.data.mapper.CardInfoMapper
import com.example.sobes.domain.model.CardInfo
import com.example.sobes.domain.repository.SectionRepository
import java.util.UUID
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val cardInfoMapper: CardInfoMapper
) : SectionRepository {

    override suspend fun getAllListSections(): List<CardInfo> {
        return database.cardInfoDao().getAllSections().map { cardInfoMapper.fromEntityToDomain(it) }
    }

    override suspend fun searchByNameSections(searchText: String): List<CardInfo> {
        return database.cardInfoDao().searchSectionsByName("%$searchText%")
            .map { cardInfoMapper.fromEntityToDomain(it) }
    }

    override suspend fun createNewSection(sectionName: String) {
        val newSection = CardInfoEntity(
            id = UUID.randomUUID().toString(),
            name = sectionName,
            countCards = 0
        )
        database.cardInfoDao().insertSection(newSection)
    }

    override suspend fun deleteSelectedSection(sectionId: String) {
        database.cardInfoDao().deleteSectionById(sectionId)
    }
}