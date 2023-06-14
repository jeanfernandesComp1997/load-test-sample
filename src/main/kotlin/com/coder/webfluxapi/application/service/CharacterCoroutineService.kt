package com.coder.webfluxapi.application.service

import com.coder.webfluxapi.domain.entitie.CartoonCharacter

interface CharacterCoroutineService {

    suspend fun retrieveCharacterById(id: Int): CartoonCharacter
    suspend fun retrieveCharacterByIdWithLocation(id: Int): CartoonCharacter
}