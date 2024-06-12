package com.example.sobes.domain.repository

import com.example.sobes.domain.model.CardInfo

interface SectionRepository {

    suspend fun getAllListSections() : List<CardInfo>

    suspend fun searchByNameSections(searchText: String) : List<CardInfo>

    suspend fun createNewSection(sectionName: String)

    suspend fun deleteSelectedSection(sectionId : String)
}