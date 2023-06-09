package com.coder.webfluxapi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfiguration(
    @Value(value = "\${http.timeoutInSeconds}") private val timeout: Long
) {

    private fun customHttpClient(): HttpClient {
        return HttpClient.create()
            .responseTimeout(Duration.ofSeconds(timeout))
    }

    @Bean
    fun createRickAndMortyWebClient(@Value(value = "\${http.rickAndMortyBaseUri}") baseUri: String): WebClient {
        return WebClient.builder()
            .baseUrl(baseUri)
            .clientConnector(ReactorClientHttpConnector(customHttpClient()))
            .build()
    }
}