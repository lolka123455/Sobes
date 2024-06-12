package com.example.sobes.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sobes.data.database.dao.AnswerCardDao

@Entity(
    tableName = AnswerCardDao.ANSWER_CARD_TABLE,
    foreignKeys = [ForeignKey(
        entity = CardInfoEntity::class,
        parentColumns = ["id"],
        childColumns = ["sectionId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["sectionId"])]
)
data class AnswerCardEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "titleCard") val titleCard: String,
    @ColumnInfo(name = "descriptionAnswer") val descriptionAnswer: String?,
    @ColumnInfo(name = "sectionId") val sectionId: String
)