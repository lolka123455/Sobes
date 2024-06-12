package com.example.sobes.data.repository

import android.content.Context
import android.net.Uri
import com.example.sobes.data.database.AppDatabase
import com.example.sobes.data.mapper.AnswerCardMapper
import com.example.sobes.data.mapper.CardInfoMapper
import com.example.sobes.domain.model.ExportData
import com.example.sobes.domain.repository.JsonImporterRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import javax.inject.Inject

class JsonImporterRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val database: AppDatabase,
    private val answerCardMapper: AnswerCardMapper,
    private val cardInfoMapper: CardInfoMapper,
) : JsonImporterRepository {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    override fun <T> importFromJson(json: String, clazz: Class<T>): T {
        val jsonAdapter = moshi.adapter(clazz)
        return jsonAdapter.fromJson(json)!!
    }

    override suspend fun importDataFromJsonFile(uri: Uri) {
        val json = context.contentResolver.openInputStream(uri)?.use { inputStream ->
            inputStream.bufferedReader().use { it.readText() }
        } ?: return

        val dataToImport = importFromJson(json, ExportData::class.java)

        withContext(Dispatchers.IO) {
            dataToImport.sections.forEach { section ->
                val entity = cardInfoMapper.fromDomainToEntity(section)
                database.cardInfoDao().insertSection(entity)
            }

            dataToImport.answerCards.forEach { answerCard ->
                val entity = answerCardMapper.fromDomainToEntity(answerCard)
                database.answerCardDao().createAnswerCard(entity)
            }
        }
    }
}
