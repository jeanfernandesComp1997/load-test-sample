package com.coder.webfluxapi.application.service.impl

import com.coder.webfluxapi.adapater.output.http.RickAndMortyDataProvider
import com.coder.webfluxapi.adapater.output.http.model.response.CharacterDataProviderResponse
import com.coder.webfluxapi.adapater.output.http.model.response.LocationDataProviderResponse
import com.coder.webfluxapi.application.service.CharacterService
import com.coder.webfluxapi.application.utils.Utils
import com.coder.webfluxapi.domain.entitie.CartoonCharacter
import com.coder.webfluxapi.domain.entitie.Location
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
                    CartoonCharacter.createCartoonCharacter(it)
                )
            }
    }

    override fun retrieveCharacterByIdWithLocation(id: Int): Mono<CartoonCharacter> {
        return rickAndMortyDataProvider
            .findById(id)
            .flatMap { characterDataProviderResponse: CharacterDataProviderResponse ->
                val locationId = Utils.extractLocationId(characterDataProviderResponse.location.url)
                addAddressToCharacter(characterDataProviderResponse, locationId)
            }
    }

    private fun addAddressToCharacter(
        characterDataProviderResponse: CharacterDataProviderResponse,
        locationId: Int
    ): Mono<CartoonCharacter> {
        return rickAndMortyDataProvider
            .findLocationById(locationId)
            .flatMap { locationDataProviderResponse: LocationDataProviderResponse ->
                Mono.just(
                    CartoonCharacter
                        .createCartoonCharacterWithLocation(
                            characterDataProviderResponse,
                            Location.createLocation(locationDataProviderResponse)
                        )
                )
            }
    }
}