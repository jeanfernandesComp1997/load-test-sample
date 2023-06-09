package com.coder.webfluxapi.adapater.output.http

import com.coder.webfluxapi.adapater.output.http.model.CharacterDataProviderResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import reactor.util.retry.Retry
import java.time.Duration

@Component
class RickAndMortyDataProvider(
    @Qualifier("createRickAndMortyWebClient")
    private val webClient: WebClient
) {

    fun findById(id: Int): Mono<CharacterDataProviderResponse> {
        return webClient.get()
            .uri("/character/$id")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus({ code: HttpStatusCode -> code.is5xxServerError },
                { response: ClientResponse ->
                    Mono.error(
                        IllegalArgumentException("Person Not Found. ${response.bodyToMono<String>()}")
                    )
                })
            .bodyToMono(CharacterDataProviderResponse::class.java)
            .retryWhen(
                Retry
                    .fixedDelay(
                        3,
                        Duration.ofSeconds(2)
                    )
                    .filter { throwable: Throwable ->
                        throwable is IllegalArgumentException
                    }
                    .onRetryExhaustedThrow { _, _ ->
                        throw RuntimeException("RickAndMorty findById Client failed to process after max retries")
                    }
            )
    }
}