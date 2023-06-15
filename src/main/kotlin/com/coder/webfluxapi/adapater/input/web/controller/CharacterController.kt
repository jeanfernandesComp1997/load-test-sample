package com.coder.webfluxapi.adapater.input.web.controller

import com.coder.webfluxapi.adapater.input.web.controller.model.response.CharacterWithLocationResponse
import com.coder.webfluxapi.adapater.input.web.controller.model.response.MinimalCharacterResponse
import com.coder.webfluxapi.application.service.CharacterService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController()
@RequestMapping("v1/characters")
class CharacterController(
    private val characterService: CharacterService
) {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveCharacterById(@PathVariable id: Int): Mono<MinimalCharacterResponse> {
        return characterService
            .retrieveCharacterById(id)
            .map {
                MinimalCharacterResponse.toMinimalCharacterResponse(it)
            }
    }

    @GetMapping("{id}/location")
    @ResponseStatus(HttpStatus.OK)
    fun retrieveCharacterByIdWithLocation(@PathVariable id: Int): Mono<CharacterWithLocationResponse> {
        return characterService
            .retrieveCharacterByIdWithLocation(id)
            .map {
                CharacterWithLocationResponse.toCharacterWithLocationResponse(it)
            }
    }

    @GetMapping("{ids}/multiple")
    @ResponseStatus(HttpStatus.OK)
    suspend fun retrieveMultiplesCharactersById(@PathVariable ids: Array<Int>): Flux<CharacterWithLocationResponse> {
        return characterService
            .retrieveMultipleCharactersById(ids)
            .map {
                CharacterWithLocationResponse.toCharacterWithLocationResponse(it)
            }
    }
}