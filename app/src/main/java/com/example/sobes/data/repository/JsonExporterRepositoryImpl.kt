package com.example.sobes.data.repository

import android.content.Context
import android.os.Environment
import com.example.sobes.data.database.AppDatabase
import com.example.sobes.data.mapper.AnswerCardMapper
import com.example.sobes.data.mapper.CardInfoMapper
import com.example.sobes.domain.model.ExportData
import com.example.sobes.domain.repository.JsonExporterRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class JsonExporterRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase,
    private val answerCardMapper: AnswerCardMapper,
    private val cardInfoMapper: CardInfoMapper,
) : JsonExporterRepository {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun <T> exportToJson(data: T, clazz: Class<T>): String {
        val jsonAdapter = moshi.adapter(clazz)
        return jsonAdapter.toJson(data)
    }

    override suspend fun exportDataToJsonFile() {
        val allSections =
            database.cardInfoDao().getAllSections().map { cardInfoMapper.fromEntityToDomain(it) }
        val allAnswerCards = database.answerCardDao().getAllAnswerCards()
            .map { answerCardMapper.fromEntityToDomain(it) }

        val dataToExport = ExportData(allSections, allAnswerCards)
        val json = exportToJson(dataToExport, ExportData::class.java)

        saveJsonToFile(json)
    }

    private suspend fun saveJsonToFile(json: String) {
        withContext(Dispatchers.IO) {
            val downloadsDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yy-HH-mm")
            val formattedDateTime = currentDateTime.format(formatter)
            val file = File(downloadsDir, "sobes_data_$formattedDateTime.json")

            FileWriter(file).use { writer ->
                writer.write(json)
            }
        }
    }
}