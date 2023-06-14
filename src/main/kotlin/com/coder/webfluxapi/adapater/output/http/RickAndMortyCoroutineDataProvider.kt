package com.coder.webfluxapi.adapater.output.http

import com.coder.webfluxapi.adapater.output.http.model.response.CharacterDataProviderResponse
import com.coder.webfluxapi.adapater.output.http.model.response.LocationDataProviderResponse
import kotlinx.coroutines.reactor.awaitSingle
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
class RickAndMortyCoroutineDataProvider(
    @Qualifier("createRickAndMortyWebClient")
    private val webClient: WebClient
) {

    suspend fun findById(id: Int): CharacterDataProviderResponse {
        return webClient.get()
            .uri("/character/$id")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus({ code: HttpStatusCode -> code.is5xxServerError },
                { response: ClientResponse ->
                    Mono.error(
                        IllegalArgumentException("Error retrieving character. ${response.bodyToMono<String>()}")
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
            .awaitSingle()
    }

    suspend fun findLocationById(id: Int): LocationDataProviderResponse {
        return webClient.get()
            .uri("/location/$id")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus({ code: HttpStatusCode -> code.is5xxServerError },
                { response: ClientResponse ->
                    Mono.error(
                        IllegalArgumentException("Error retrieving character. ${response.bodyToMono<String>()}")
                    )
                })
            .bodyToMono(LocationDataProviderResponse::class.java)
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
                        throw RuntimeException("RickAndMorty findLocationById Client failed to process after max retries")
                    }
            )
            .awaitSingle()
    }
}