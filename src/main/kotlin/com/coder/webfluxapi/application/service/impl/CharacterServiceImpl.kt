package com.coder.webfluxapi.application.service.impl

import com.coder.webfluxapi.adapater.output.http.RickAndMortyDataProvider
import com.coder.webfluxapi.application.service.CharacterService
import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CharacterServiceImpl(
    private val rickAndMortyDataProvider: RickAndMortyDataProvider
) : CharacterService {

    override fun retrieveCharacterById(id: Int): Mono<CartoonCharacter> {
        return rickAndMortyDataProvider
            .findById(id)
            .flatMap {
                Mono.just(
                    CartoonCharacter(
                        it.id,
                        it.name,
                        isAlive(it.status),
                        it.species
                    )
                )
            }
    }

    private fun isAlive(status: String): Boolean {
        return status == "Alive"
    }
}