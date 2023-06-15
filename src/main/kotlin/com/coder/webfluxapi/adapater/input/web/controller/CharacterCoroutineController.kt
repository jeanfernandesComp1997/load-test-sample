package com.coder.webfluxapi.adapater.input.web.controller

import com.coder.webfluxapi.adapater.input.web.controller.model.response.CharacterWithLocationResponse
import com.coder.webfluxapi.adapater.input.web.controller.model.response.MinimalCharacterResponse
import com.coder.webfluxapi.application.service.CharacterCoroutineService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RestController
@RequestMapping("v2/characters")
class CharacterCoroutineController(
    private val characterService: CharacterCoroutineService
) {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun retrieveCharacterById(@PathVariable id: Int): MinimalCharacterResponse {
        return characterService
            .retrieveCharacterById(id)
            .let {
                MinimalCharacterResponse.toMinimalCharacterResponse(it)
            }
    }

    @GetMapping("{id}/location")
    @ResponseStatus(HttpStatus.OK)
    suspend fun retrieveCharacterByIdWithLocation(@PathVariable id: Int): CharacterWithLocationResponse {
        return characterService
            .retrieveCharacterByIdWithLocation(id)
            .let {
                CharacterWithLocationResponse.toCharacterWithLocationResponse(it)
            }
    }

    @GetMapping("{ids}/multiple")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveMultiplesCharactersById(@PathVariable ids: Array<Int>): Flow<CharacterWithLocationResponse> {
        return characterService
            .retrieveMultipleCharactersById(ids)
            .map {
                CharacterWithLocationResponse.toCharacterWithLocationResponse(it)
            }
    }
}