package com.coder.webfluxapi.application.service.impl

import com.coder.webfluxapi.adapater.output.http.RickAndMortyCoroutineDataProvider
import com.coder.webfluxapi.application.service.CharacterCoroutineService
import com.coder.webfluxapi.application.utils.Utils
import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import com.coder.webfluxapi.domain.entitie.Location
import org.springframework.stereotype.Service

@Service
class CharacterCoroutineServiceImpl(
    private val rickAndMortyDataProvider: RickAndMortyCoroutineDataProvider
) : CharacterCoroutineService {

    override suspend fun retrieveCharacterById(id: Int): CartoonCharacter {
        return rickAndMortyDataProvider
            .findById(id)
            .let {
                CartoonCharacter.createCartoonCharacter(it)
            }
    }

    override suspend fun retrieveCharacterByIdWithLocation(id: Int): CartoonCharacter {
        val character = rickAndMortyDataProvider.findById(id)
        val location = rickAndMortyDataProvider
            .findLocationById(
                Utils.extractLocationId(character.location.url)
            ).let {
                Location.createLocation(it)
            }
        return CartoonCharacter
            .createCartoonCharacterWithLocation(
                character,
                location
            )
    }
}