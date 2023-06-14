package com.coder.webfluxapi.adapater.input.web.controller.model.response

import com.coder.webfluxapi.domain.entitie.Location
import java.time.LocalDate

data class CharacterLocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val created: LocalDate
) {

    companion object {
        fun toCharacterLocationResponse(location: Location): CharacterLocationResponse {
            return CharacterLocationResponse(
                location.id,
                location.name,
                location.type,
                location.dimension,
                location.created
            )
        }
    }
}