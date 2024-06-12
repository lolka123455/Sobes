package com.example.sobes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sobes.data.database.dao.AnswerCardDao
import com.example.sobes.data.database.dao.CardInfoDao
import com.example.sobes.data.database.entity.AnswerCardEntity
import com.example.sobes.data.database.entity.CardInfoEntity

@Database(
    entities = [
        AnswerCardEntity::class,
        CardInfoEntity::class,
    ],
    version = AppDatabase.DATABASE_VERSION
)

abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "app_database"
    }

    abstract fun answerCardDao(): AnswerCardDao

    abstract fun cardInfoDao(): CardInfoDao

}