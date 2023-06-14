package com.coder.webfluxapi.application.utils

object Utils {

    fun extractLocationId(locationUrl: String): Int {
        return locationUrl
            .split("/")
            .last()
            .toInt()
    }
}