package com.coder.webfluxapi.domain.entitie

import com.coder.webfluxapi.adapater.output.http.model.response.LocationDataProviderResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val created: LocalDate
) {

    companion object {

        fun createLocation(locationDataProviderResponse: LocationDataProviderResponse): Location {
            return Location(
                locationDataProviderResponse.id,
                locationDataProviderResponse.name,
                locationDataProviderResponse.type,
                locationDataProviderResponse.dimension,
                LocalDate.parse(locationDataProviderResponse.created, DateTimeFormatter.ISO_DATE_TIME)
            )
        }
    }
}