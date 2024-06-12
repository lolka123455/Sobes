package com.example.sobes.domain.repository

import android.net.Uri

interface JsonImporterRepository {
    fun <T> importFromJson(json: String, clazz: Class<T>): T

    suspend fun importDataFromJsonFile(uri: Uri)
}
