package com.example.sobes.domain.usecase.json

import android.net.Uri
import com.example.sobes.domain.repository.JsonImporterRepository
import javax.inject.Inject

class ImportDataUseCase @Inject constructor(
    private val jsonImporterRepository: JsonImporterRepository
) {
    suspend fun execute(uri: Uri) {
        jsonImporterRepository.importDataFromJsonFile(uri)
    }
}
