package com.example.sobes.domain.repository

interface JsonExporterRepository {
    fun <T> exportToJson(data: T, clazz: Class<T>): String

    suspend fun exportDataToJsonFile()
}
