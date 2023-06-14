package com.coder.webfluxapi.application.service

import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import reactor.core.publisher.Mono

interface CharacterService {

    fun retrieveCharacterById(id: Int): Mono<CartoonCharacter>
    fun retrieveCharacterByIdWithLocation(id: Int): Mono<CartoonCharacter>
}