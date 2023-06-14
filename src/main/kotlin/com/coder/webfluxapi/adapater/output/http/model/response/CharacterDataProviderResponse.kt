package com.coder.webfluxapi.adapater.output.http.model.response

data class CharacterDataProviderResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: CharacterOriginResponse,
    val image: String,
    val location: CharacterLocationResponse
)

data class CharacterOriginResponse(
    val name: String
)

data class CharacterLocationResponse(
    val url: String
)