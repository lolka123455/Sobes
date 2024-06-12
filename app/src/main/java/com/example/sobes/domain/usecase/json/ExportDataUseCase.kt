package com.example.sobes.domain.usecase.json

import com.example.sobes.domain.repository.JsonExporterRepository
import javax.inject.Inject

class ExportDataUseCase @Inject constructor(
    private val jsonExporterRepository: JsonExporterRepository
) {
    suspend fun execute() {
        jsonExporterRepository.exportDataToJsonFile()
    }
}
