package com.coder.webfluxapi.adapater.input.web.controller.model.response

import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import com.fasterxml.jackson.annotation.JsonProperty

data class CharacterWithLocationResponse(
    val id: Int,
    val name: String,
    @JsonProperty("is_alive")
    val isAlive: Boolean,
    val species: String,
    val location: CharacterLocationResponse
) {

    companion object {
        fun toCharacterWithLocationResponse(character: CartoonCharacter): CharacterWithLocationResponse {
            return CharacterWithLocationResponse(
                character.id,
                character.name,
                character.isAlive,
                character.species,
                CharacterLocationResponse.toCharacterLocationResponse(character.location!!)
            )
        }
    }
}