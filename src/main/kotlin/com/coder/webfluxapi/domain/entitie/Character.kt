package com.coder.webfluxapi.domain.entitie

import com.coder.webfluxapi.adapater.output.http.model.response.CharacterDataProviderResponse

class CartoonCharacter(
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val species: String,
    val location: Location? = null
) {

    companion object {

        fun createCartoonCharacter(characterDataProviderResponse: CharacterDataProviderResponse): CartoonCharacter {
            return CartoonCharacter(
                characterDataProviderResponse.id,
                characterDataProviderResponse.name,
                isAlive(characterDataProviderResponse.status),
                characterDataProviderResponse.species
            )
        }

        fun createCartoonCharacterWithLocation(
            characterDataProviderResponse: CharacterDataProviderResponse,
            location: Location
        ): CartoonCharacter {
            return CartoonCharacter(
                characterDataProviderResponse.id,
                characterDataProviderResponse.name,
                isAlive(characterDataProviderResponse.status),
                characterDataProviderResponse.species,
                location
            )
        }

        private fun isAlive(status: String): Boolean {
            return status == "Alive"
        }
    }
}