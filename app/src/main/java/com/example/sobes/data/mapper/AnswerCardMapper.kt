package com.example.sobes.data.mapper

import com.example.sobes.data.database.entity.AnswerCardEntity
import com.example.sobes.domain.model.AnswerCard
import javax.inject.Inject

class AnswerCardMapper @Inject constructor() {

    fun fromEntityToDomain(entity: AnswerCardEntity): AnswerCard {
        return AnswerCard(
            id = entity.id,
            titleCard = entity.titleCard,
            descriptionAnswer = entity.descriptionAnswer,
            sectionId = entity.sectionId
        )
    }

    fun fromDomainToEntity(domain: AnswerCard): AnswerCardEntity {
        return AnswerCardEntity(
            id = domain.id,
            titleCard = domain.titleCard,
            descriptionAnswer = domain.descriptionAnswer,
            sectionId = domain.sectionId
        )
    }


}