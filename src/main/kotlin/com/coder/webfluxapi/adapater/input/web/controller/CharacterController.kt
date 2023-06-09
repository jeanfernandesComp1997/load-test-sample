package com.coder.webfluxapi.adapater.input.web.controller

import com.coder.webfluxapi.adapater.input.web.controller.model.MinimalCharacterResponse
import com.coder.webfluxapi.application.service.CharacterService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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
}