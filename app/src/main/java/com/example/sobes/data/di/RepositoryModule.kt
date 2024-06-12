package com.example.sobes.data.di

import com.example.sobes.data.repository.AnswersRepositoryImpl
import com.example.sobes.data.repository.JsonExporterRepositoryImpl
import com.example.sobes.data.repository.JsonImporterRepositoryImpl
import com.example.sobes.data.repository.SectionRepositoryImpl
import com.example.sobes.domain.repository.AnswersRepository
import com.example.sobes.domain.repository.JsonExporterRepository
import com.example.sobes.domain.repository.JsonImporterRepository
import com.example.sobes.domain.repository.SectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsAnswerRepository(answersRepositoryImpl: AnswersRepositoryImpl): AnswersRepository

    @Binds
    abstract fun bindsSectionRepository(sectionRepositoryImpl: SectionRepositoryImpl): SectionRepository

    @Binds
    abstract fun bindsJsonExporterRepository(jsonExporterRepositoryImpl: JsonExporterRepositoryImpl): JsonExporterRepository

    @Binds
    abstract fun bindsJsonImporterRepository(jsonImporterRepositoryImpl: JsonImporterRepositoryImpl): JsonImporterRepository


}