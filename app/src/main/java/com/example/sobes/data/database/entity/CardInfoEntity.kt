package com.example.sobes.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sobes.data.database.dao.CardInfoDao

@Entity(tableName = CardInfoDao.CARD_INFO_TABLE)
data class CardInfoEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "countCards") val countCards: Int,
)