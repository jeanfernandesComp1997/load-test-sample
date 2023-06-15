package com.coder.webfluxapi.application.service

import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterCoroutineService {

    suspend fun retrieveCharacterById(id: Int): CartoonCharacter
    suspend fun retrieveCharacterByIdWithLocation(id: Int): CartoonCharacter
    fun retrieveMultipleCharactersById(ids: Array<Int>): Flow<CartoonCharacter>
}