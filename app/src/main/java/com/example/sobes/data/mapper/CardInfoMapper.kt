package com.example.sobes.data.mapper

import com.example.sobes.data.database.entity.CardInfoEntity
import com.example.sobes.domain.model.CardInfo
import javax.inject.Inject

class CardInfoMapper @Inject constructor() {

    fun fromEntityToDomain(entity: CardInfoEntity): CardInfo {
        return CardInfo(
            id = entity.id,
            name = entity.name,
            countCards = entity.countCards
        )
    }

    fun fromDomainToEntity(domain: CardInfo): CardInfoEntity {
        return CardInfoEntity(
            id = domain.id,
            name = domain.name,
            countCards = domain.countCards
        )
    }

}