package com.coder.webfluxapi.adapater.input.web.controller.model

import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import com.fasterxml.jackson.annotation.JsonProperty

data class MinimalCharacterResponse(
    val id: Int,
    val name: String,
    @JsonProperty("is_alive")
    val isAlive: Boolean,
    val species: String
) {

    companion object {
        fun toMinimalCharacterResponse(character: CartoonCharacter): MinimalCharacterResponse {
            return MinimalCharacterResponse(
                character.id,
                character.name,
                character.isAlive,
                character.species
            )
        }
    }
}